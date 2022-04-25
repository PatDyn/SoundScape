scripts
-------

This directory contains scripts for the `SoundScape` project, in particular:

- [Retrieve `ammenities` from OpenStreetMap](#retrieve-ammenities-from-openstreetmap)
- Retrieve

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
