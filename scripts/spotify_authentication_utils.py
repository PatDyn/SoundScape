import os
import base64

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
