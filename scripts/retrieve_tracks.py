import json
import argparse
import requests
from spotify_authentication_utils import authorize_spotify


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Tracks retriever")
    parser.add_argument('tracks', nargs='+', default=[], help="The tracks you want to fetch info about")
    args = parser.parse_args()
    access_token = authorize_spotify()

    response = requests.get("https://api.spotify.com/v1/tracks", headers={
        'Authorization': f"Bearer {access_token}"
    }, params = {
        'ids': ",".join(args.tracks)
    })
    response.raise_for_status()
    tracks_data = response.json()
    for track in tracks_data.get("tracks"):
        print(json.dumps(track))
