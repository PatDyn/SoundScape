import json
import requests
from spotify_authentication_utils import authorize_spotify


if __name__ == '__main__':
    access_token = authorize_spotify()
    
    response = requests.get("https://api.spotify.com/v1/recommendations/available-genre-seeds", headers={
        'Authorization': f"Bearer {access_token}"
    })
    response.raise_for_status()
    available_genre_seeds = response.json()
    print(json.dumps(available_genre_seeds))
