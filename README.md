# mntr [![Build](https://github.com/Shpota/mntr/actions/workflows/build.yml/badge.svg)](https://github.com/Shpota/mntr/actions/workflows/build.yml)

A simple web app that allows to monitor http services.

## Preconditions

- You have to have Docker and docker-compose installed in order to build and run the application
- The 8080 and 3000 ports have to be free

## How to build and run

1. Build Docker images:
```sh
docker-compose build
```
2. Start the application:
```sh
docker-compose up
```
Once the application is started it will be available on http://localhost:3000

## What needs improvements

- the app has to be covered with tests
- timeouts for fetching URLs
- better UI 