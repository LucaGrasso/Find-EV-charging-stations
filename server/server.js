// server.js
// where your node app starts

// init project
var express = require('express');
var bodyParser = require('body-parser');
var app = express();
app.use(bodyParser.urlencoded({ extended: true }));

// we've started you off with Express, 
// but feel free to use whatever libs or frameworks you'd like through `package.json`.

// http://expressjs.com/en/starter/static-files.html
app.use(express.static('public'));

// init sqlite db
var fs = require('fs');
var dbFile = 'sqlite.db';
var exists = fs.existsSync(dbFile);
var sqlite3 = require('sqlite3').verbose();
var db = new sqlite3.Database(dbFile);

// if ./.data/sqlite.db does not exist, create it, otherwise print records to console
db.serialize(function(){
  if (!exists) {
    db.run('CREATE TABLE Commenti (commento TEXT, colonnina INT)');
    console.log('New table Commenti created!');
    
    // insert default dreams
    db.serialize(function() {
      db.run('INSERT INTO Commenti (commento, colonnina) VALUES ("ok", "01")');
    });
  }
  else {
    console.log('Database "Commenti" ready to go!');
    db.each('SELECT * from Commenti', function(err, row) {
      if ( row ) {
        console.log('record:', row);
      }
    });
  }
});

// http://expressjs.com/en/starter/basic-routing.html
app.get('/', function(request, response) {
  response.sendFile(__dirname + '/views/index.html');
});

// endpoint to get all the dreams in the database
// currently this is the only endpoint, ie. adding dreams won't update the database
// read the sqlite3 module docs and try to add your own! https://www.npmjs.com/package/sqlite3
app.get('/getCommenti', function(request, response) {
  db.all('SELECT * from Commenti', function(err, rows) {
    response.send(JSON.stringify(rows));
  });
});

app.post('/storeComment', (request, response) => {
  const postBody = request.body;
  db.run(`INSERT INTO Commenti(commento, colonnina) VALUES(?, ?)`, [postBody.commento, postBody.colonnina], function(err) {
    if (err) {
      return console.log(err.message);
    }
    // get the last insert id
    console.log(`A row has been inserted with rowid ${this.lastID}`);
  });
  response.sendFile('views/index.html', {root: __dirname })
});

app.get('/trova-colonnine', function (req, res) {
	
	res.status(200);
	
	const geolib = require('geolib');
	
	var propertiesObject = { output:'json', latitude:req.query.latitude, longitude:req.query.longitude, maxresults:'10'};
	
	var request = require("request");

  request({url:"https://api.openchargemap.io/v3/poi/", qs:propertiesObject}, function(error, response, body) {
		if(JSON.parse(body).length == 0){
			var o = {};
			res.json(o);
		} else {
			var mindist = 0;
			var nullpowercolonnine = [];
			var mindata = {};
			for(var i=0; i<9; i++){
				var colonnina = JSON.parse(body)[i];
				var arrayconnections = colonnina.Connections;
				var lat = colonnina.AddressInfo.Latitude;
				var lon = colonnina.AddressInfo.Longitude;
				var dist = geolib.getDistance(
					{ latitude: parseFloat(req.query.latitude), longitude: parseFloat(req.query.latitude) },
					{ latitude: parseFloat(lat), longitude: parseFloat(lon) }
				);
					
			for(var j=0; arrayconnections.length; j++){
				var minpow = parseFloat(req.query.minpowerkw);
        console.log(arrayconnections[j].PowerKW);
				if(arrayconnections[j].PowerKW != 'undefined' && arrayconnections[j].PowerKW >= minpow) {
					
					if(dist < mindist || mindist == 0) {
						mindist = dist;
						mindata = {'Latitude': lat, 'Longitude': lon, 'distance': mindist};
					}
					break;	
				} else if(arrayconnections[j].PowerKW == 'undefined'){
					var newdata = {'Latitude': lat, 'Longitude': lon, 'distance': dist};
					nullpowercolonnine.push(newdata);
					break;
				}
			}
			
			
			
		}
		
			if(nullpowercolonnine.length==0 && mindist == 0){
				var o = {};
				res.json(o);
			} else {
				//var finaldata = {'notnullcolonnina': mindata, 'nullcolonnina': nullpowercolonnine};
        nullpowercolonnine.push(mindata);
        var finaldata = nullpowercolonnine;
				res.json(finaldata);
			}               
		
		}
	});
});

// listen for requests :)
var listener = app.listen(process.env.PORT, function() {
  console.log('Your app is listening on port ' + listener.address().port);
});
