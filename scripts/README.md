scripts
-------

This directory contains scripts for the `SoundScape` project, in particular:

- [Retrieve `ammenities` from OpenStreetMap](#retrieve-ammenities-from-openstreetmap)
- [Retrieve genre seeds from Spotify](#retrieve-genre-seeds-from-spotify)
- Retrieve track information from Spotify
- Retrieve audio features from Spotify

# Setup

1. `python3 -m venv venv` (or `pyenv` etc)
2. `source venv/bin/activate`
3. `pip3 install -r requirements.txt`
4. If you want to do some JSON post-processing, use `jq` from your package manager of choosing

# Retrieve ammenities from OpenStreetMap

An `ammenity` in terms of OpenStreetMap (OSM) is any kind of physical location that, beyond being a physical point, has associated user data.
The `retrieve_ammenities` script is designed to fetch this information from OSM. The default ammenity is `bar`.

Use `python3 retrieve_ammenities.py --help` for help. Example usage is:

```
python3 retrieve_ammenities.py "Wilhelmstraße 30/1, 72074 Tübingen" >> bars_tuebingen.jsonl
cat bars_tuebingen.jsonl | jq -r '.tags.name'

>> Bartista
>> Zur Stadtpost
>> Saints & Scholars
>> Asmara
>> Arsenal
>> Last Resort
>> ShooterStars
>> achtbar
>> Schmitz Katze
>> Blue Bay
>> Esperanto - Shisha- Lounge & Bar
>> Skybar
```

The full list of available ammenity types is on the [OSM wiki](https://wiki.openstreetmap.org/wiki/Key:amenity).

# Setup Spotify

Follow the [Spotify Authorization guide](https://developer.spotify.com/documentation/general/guides/authorization/). For the scripts we're building a __server__ type application. You will get a `CLIENT_ID` and `CLIENT_SECRET`.

Spotify scripts generally assume you have those set up as environment variables, e.g like so:

```
export CLIENT_ID=abc
export CLIENT_SECRET=def
```

# Retrieve Genre Seeds from Spotify

If you've set up your Spotify this is as easy as running

```
python3 retrieve_genres.py

>>> acoustic
>>> afrobeat
>>> alt-rock
>>> alternative
>>> ambient
>>> anime
>>> black-metal
>>> bluegrass
>>> blues
>>> bossanova
>>> .......
```

# Retrieve track information and features

Given some track IDs you can use the two scripts to retrieve their information and features respectively.

For instance, given the tracks `7ouMYWpwJ422jRcDASZB7P 4VqPOruhp5EdPBeR92t6lQ 2takcwOaAZWiXQijPHIx7B`: 

```
python3 retrieve_tracks.py 7ouMYWpwJ422jRcDASZB7P 4VqPOruhp5EdPBeR92t6lQ 2takcwOaAZWiXQijPHIx7B >>
tracks.jsonl
cat tracks.jsonl | jq -r '.name'

>>> Knights of Cydonia
>>> Uprising
>>> Time is Running Out

python3 retrieve_track_features.py 7ouMYWpwJ422jRcDASZB7P 4VqPOruhp5EdPBeR92t6lQ 2takcwOaAZWiXQijPHIx7B >> track_features.jsonl
head -n 1 track_features.jsonl | jq '.'

>>> {
>>>   "danceability": 0.366,
>>>   "energy": 0.963,
>>>   "key": 11,
>>>   "loudness": -5.301,
>>>   "mode": 0,
>>>   "speechiness": 0.142,
>>>   "acousticness": 0.000273,
>>>   "instrumentalness": 0.0122,
>>>   "liveness": 0.115,
>>>   "valence": 0.211,
>>>   "tempo": 137.114,
>>>   "type": "audio_features",
>>>   "id": "7ouMYWpwJ422jRcDASZB7P",
>>>   "uri": "spotify:track:7ouMYWpwJ422jRcDASZB7P",
>>>   "track_href": "https://api.spotify.com/v1/tracks/7ouMYWpwJ422jRcDASZB7P",
>>>   "analysis_url": "https://api.spotify.com/v1/audio-analysis/7ouMYWpwJ422jRcDASZB7P",
>>>   "duration_ms": 366213,
>>>   "time_signature": 4
>>> }
```
