# Thinking application
This repository contains the sources of the *Thinking* application, which has been developed and employed as use case for (fault-aware) management protocols. If you wish to reuse this application, please cite the following paper:
```
@inproceedings{Brogi2016,
 author = {Antonio Brogi and Andrea Canciani and Jacopo Soldani},
 title = {Fault-aware application management protocols},
 editor = {Aiello, Marco and Johnsen, Einar Broc and Dustdar, Schahram and Georgievski, Ilche},
 booktitle = {Service-Oriented and Cloud Computing: Fifth European Conference, ESOCC 2016, Vienna, Austria, September 5-7, 2016. Proceedings},
 year = {2016},
 publisher = {Springer},
 pages = {219-234}, 
 doi = {10.1007/978-3-319-44482-6\_14}
} 
```

## Short description
Thinking is composed by three main components: 
1. An instance of MongoDB that is exploited to permanently store the collection of thoughts shared by end-users, 
2. a Dropwizard-based [REST API](https://github.com/di-unipi-socc/thinking/tree/master/api) that permits remotely accessing the collection of shared thoughts, and
3. a web-based [GUI](https://github.com/di-unipi-socc/thinking/tree/master/gui) that interacts with the REST API to permit retrieving and adding thoughts to the shared collection. 

The MongoDB instance is obtained by instantiating a Mongo Docker container, while ThoughtsApi and ThoughtsGui are made concrete by hosting them on a Maven Docker container and on a Node Docker container.
