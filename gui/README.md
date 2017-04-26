## Thinking Application - NodeJS-based GUI
The web-based GUI is thought to be offered through a [node](https://hub.docker.com/_/node/) container. To set up the application please proceed as follows:

(a) Download the repository. (In the following we will assume to have downloaded it in _$PWD/thoughts-gui_) 

(b) Run a node container (in interactive mode, by also indicating the host's port where to map the container's port 3000)
```
docker run -it -v "$PWD/thoughts-gui":/thoughts-gui -p 3000:3000 --name thoughtsnode node /bin/bash
```

(c) Install the web-based GUI in the container.
```
thoughts-gui/scripts/install.sh
```

(d) Configure the web-based GUI to connect it to an instance of the [REST API](https://github.com/jacopogiallo/thoughts-api)
```
thoughts-gui/scripts/configure.sh apiUrl apiPort apiResource
```
For instance, assume to have the REST API running locally on port 8282, and offering its capabilities at the resource _thoughts_ 
```
thoughts-api/scripts/configure.sh localhost 8282 thoughts
``` 

(e) Start the NodeJS server offering the web-based GUI.
```
thoughts-gui/scripts/start.sh
```

Note: If you wish to stop and/or uninstall the server offering the GUI, please use the corresponding scripts.
