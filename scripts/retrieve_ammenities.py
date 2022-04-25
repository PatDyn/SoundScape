import argparse
import json
import sys

import requests
from pygeohash import encode


if __name__ == "__main__":
    """
    Runs an Overpass Query for bars at the given address.
    Prints a JSONL file of all returned elements.
    
    See https://help.openstreetmap.org/questions/80922/how-to-search-for-restaurants-near-by-an-address/81669
    Toy around with this at https://overpass-turbo.eu/
    """

    parser = argparse.ArgumentParser(description="Ammenity retriever")
    parser.add_argument('address', type=str, help='The address to look up')
    parser.add_argument('ammenity', nargs='?', default="bar", help="The ammenity type")
    parser.add_argument('around', nargs='?', default=1000, help="The radius to 'look around'")
    args = parser.parse_args()

    # address = "Platz der Republik 1, 10557 Berlin"
    nominatim = "https://nominatim.openstreetmap.org/search"
    search_response = requests.get(nominatim, params={
        'format': 'json',
        'q': args.address
    })
    places = search_response.json()
    place = places[0]
    latitude = place["lat"]
    longitude = place["lon"]

    api_url = "https://overpass-api.de/api/interpreter"
    query = f"""
    [out:json][timeout:25];
    (nwr["amenity"="{args.ammenity}"](around:{args.around},{latitude},{longitude});) -> .results;
    .results out center;
    """
    response = requests.get(api_url, params={
        "data": query
    })
    response.raise_for_status()
    data = response.json()
    for element in data.get("elements", []):
        element["geohash"] = encode(element["lat"], element["lon"])
        print(json.dumps(element), file=sys.stdout)
