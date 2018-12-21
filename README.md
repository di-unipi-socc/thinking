# _Thinking_ 
This repository contains the sources of the *Thinking* application, which has been developed and employed as use case for (fault-aware) management protocols. If you wish to reuse this application, please cite the following paper:
```
@article{fault-aware-management-protocols,
  title = {Fault-aware management protocols for multi-component applications},
  journal = {Journal of Systems and Software},
  volume = {139},
  pages = {189 - 210},
  year = {2018},
  issn = {0164-1212},
  doi = {10.1016/j.jss.2018.02.005},
  author = {Antonio Brogi and Andrea Canciani and Jacopo Soldani}
}
```

## Short description
Thinking is composed by three main components: 
1. An instance of MongoDB that is exploited to permanently store the collection of thoughts shared by end-users, 
2. a Dropwizard-based [REST API](https://github.com/di-unipi-socc/thinking/tree/master/api) that permits remotely accessing the collection of shared thoughts, and
3. a web-based [GUI](https://github.com/di-unipi-socc/thinking/tree/master/gui) that interacts with the REST API to permit retrieving and adding thoughts to the shared collection. 

The MongoDB instance is obtained by instantiating a Mongo Docker container, while ThoughtsApi and ThoughtsGui are made concrete by hosting them on a Maven Docker container and on a Node Docker container.

## Running an instance of _Thinking_
### Step 1 - MongoDB
The MongoDB instance is obtained by instantiating a [mongo](https://hub.docker.com/_/mongo/) Docker container (called `thinkingdb`) which exposes a port `p` for connecting to it. For instance, if `p` is `27017`: 
```
docker run -name thinkingdb -p 27017:27017 -d mongo
```
### Step 2 - REST API
The REST API is thought to run in a [maven](https://hub.docker.com/_/maven/) container. To set up the application please proceed as follows:

(a) Download the repository. (In the following we will assume to have downloaded it in `$PWD/thoughts-api`) 

(b) Run a maven container (called `thoughtsapi`). The container should be run in _interactive mode_, by specifying the volume where to retrieve the API sources (e.g., `$PWD/thoughts-api`, which has to be mapped to `/thoughts-api`) and the host's port where to map the container's port `8080` (e.g., `8282`).
```
docker run -it -v "$PWD/thoughts-api":/thoughts-api -p 8282:8080 --name thinkingapi maven /bin/bash
```

(c) Install the REST API in the container.
```
thoughts-api/scripts/install.sh
```

(d) Configure the application to connect it to `thinkingdb`
```
thoughts-api/scripts/configure.sh 172.17.0.1 27017 thoughtsSharing thoughts
``` 

(e) Start the application offering the REST API.
```
thoughts-api/scripts/start.sh
```

### Step 3 - GUI
The web-based GUI is thought to be offered through a [node](https://hub.docker.com/_/node/) container. To set up the application please proceed as follows:

(a) Download the repository. (In the following we will assume to have downloaded it in _$PWD/thoughts-gui_) 

(b) Run a node container (called `thoughtsgui`) in interactive mode, by also indicating the host's port where to map the container's port `3000`.
```
docker run -it -v "$PWD/thoughts-gui":/thoughts-gui -p 3000:3000 --name thinkinggui node /bin/bash
```

(c) Install the web-based GUI in the container.
```
thoughts-gui/scripts/install.sh
```

(d) Configure the web-based GUI to connect it to the running instance of the REST API:
```
thoughts-api/scripts/configure.sh localhost 8282 thoughts
``` 

(e) Start the NodeJS server offering the web-based GUI.
```
thoughts-gui/scripts/start.sh
```

## Management
### Mongo
A MongoDB instance can be managed as follows. 
* _Run_ -> `docker run -name thinkingdb -p 27017:27017 -d mongo`
* _Start_ -> `docker start thinkingdb` 
* _Stop_ -> `docker stop thinkingdb`
* _Delete_ -> `docker rm thinkingdb`

### REST API
To install, configure, start, stop or uninstall an instance of the REST API, please use the corresponding bash script (available in the [api/scripts](https://github.com/di-unipi-socc/thinking/tree/master/api/scripts) sub-folder) in a running instance of a [maven](https://hub.docker.com/_/maven/) container. 

### GUI
To install, configure, start, stop or uninstall an instance of the web-based GUI, please use the corresponding bash script (available in the [gui/scripts](https://github.com/di-unipi-socc/thinking/tree/master/gui/scripts) sub-folder) in a running instance of a [node](https://hub.docker.com/_/node/) container. 

