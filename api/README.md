# Thinking Application - REST API

## Documentation

The REST API is thought to run in a [maven](https://hub.docker.com/_/maven/) container. To set up the application please proceed as follows:

(a) Download the repository. (In the following we will assume to have downloaded it in _$PWD/thoughts-api_) 

(b) Run a maven container. The container should be run in _interactive mode_, by specifying the volume where to retrieve the API sources (e.g., _$PWD/thoughts-api_, which has to be mapped to _/thoughts-api_) and the host's port where to map the container's port 8080 (e.g., 8282).
```
docker run -it -v "$PWD/thoughts-api":/thoughts-api -p 8282:8080 --name thoughtsmaven maven /bin/bash
```

(c) Install the application in the container.
```
thoughts-api/scripts/install.sh
```

(d) Configure the application to connect it to a MongoDB instance
```
thoughts-api/scripts/configure.sh dbURL dbPort dbName collectionName
```
For instance, assume to have MongoDB running locally on port 27017. We set the the URL to the gateway of the container (viz., 172.17.0.1).
```
thoughts-api/scripts/configure.sh 172.17.0.1 27017 thoughtsSharing thoughts
``` 

(e) Start the application offering the REST API.
```
thoughts-api/scripts/start.sh
```

Note: If you wish to stop and/or uninstall the application from the container, please use the corresponding scripts.
