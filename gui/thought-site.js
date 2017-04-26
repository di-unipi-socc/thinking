var express = require('express');
var serveStatic = require('serve-static');
var cors = require('cors');

var app = express();

app.use(cors());
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

//app.use(express.static('public'));
app.use(serveStatic(__dirname + '/public'));

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});
