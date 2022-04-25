import os
import base64
import json

import requests


def authorize_spotify() -> str:
    CLIENT_ID = os.environ.get('CLIENT_ID')
    CLIENT_SECRET = os.environ.get('CLIENT_SECRET')
    token_auth = ":".join([CLIENT_ID, CLIENT_SECRET])

    response = requests.post("https://accounts.spotify.com/api/token", data={
        'grant_type': 'client_credentials',
    }, headers={
        'Authorization': f"Basic {base64.b64encode(token_auth.encode()).decode()}"
    })

    response.raise_for_status()
    token_data = response.json()
    return token_data.get('access_token')


if __name__ == '__main__':
    access_token = authorize_spotify()
    
    response = requests.get("https://api.spotify.com/v1/recommendations/available-genre-seeds", headers={
        'Authorization': f"Bearer {access_token}"
    })
    response.raise_for_status()
    available_genre_seeds = response.json()
    print(json.dumps(available_genre_seeds))
