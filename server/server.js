// author: Luca Grasso
// Exam project

/* I file del server sono caricati su 
http://find-ev-charging-stations.glitch.me/ */

// I commenti in inglese fanno riferimento alle documentazioni della libreria

/*****************************************************************************/
/*                                   server.js								 */
/*								It's a nodeJS app			                 */
/*****************************************************************************/

/* Esempio di get 
http://find-ev-charging-stations.glitch.me/trova-colonnine/?longitude=40&latitude8&minpowerkw=2
*/


/*                                 Init project                               */
/*----------------------------------------------------------------------------*/
/*              carico le librerie necessarie al progetto                     */
var express = require('express');        // dipendenza di nodejs per ascoltare sulla porta 3000 vedi documentazione nodeJS
var bodyParser = require('body-parser'); // carico il body-parse per estrarre informazioni dalla request e respost
var app = express();					 // si avvia l'applicazione server come express in ascolto su 3000

// The middleware to handle url encoded data is returned by bodyParser.urlencoded({extended: false}).
// extended=falseis a configuration option that tells the parser to use the classic encoding.
// When using it, values can be only strings or arrays.
// The extended version allows more data flexibility, but it is outmatched by JSON. 
app.use(bodyParser.urlencoded({ extended: true })); //ritorno in JSON

/*Express, by default does not allow you to serve static files.
  You need to enable it using the following built-in middleware.
  Express looks up the files relative to the static directory,
  so the name of the static directory is not part of the URL.*/
// Indico a express di utilizzare il file Js/Css nella cartella Public utile per la versione web del client di test
  app.use(express.static('public'));     
									
// INIZIALIZZAZIONE DATABASE SQLITE
/***********************************************************************/
// variabili per inizializzare un server sqlite per i commenti
var fs = require('fs');                     // require del Nodejs
var dbFile = 'sqlite.db';					// definisco il nome del file db da utilizzare
var exists = fs.existsSync(dbFile);         // variabile per verifica dell'esistenza del file
var sqlite3 = require('sqlite3').verbose(); // il modulo della dipendenza deve essere chiamato prima dell'utilizzo
var db = new sqlite3.Database(dbFile);      // creo l'oggetto per la lettura del database

// if ./.data/sqlite.db does not exist, create it, otherwise print records to console
// se non esiste il Database viene creato con colonne specifiche per il progetto
db.serialize(function(){
  if (!exists) {														// se non esiste
    db.run('CREATE TABLE Commenti (commento TEXT, colonnina INT)');     // creo la tabella Commento e colonnina numeto
    console.log('New table Commenti created!');							// mando nel log la creazione
    
	// insert default dreams
	// creo un record di test sembra necessario per la creazione
    db.serialize(function() {
      db.run('INSERT INTO Commenti (commento, colonnina) VALUES ("ok", "01")');  // inserito record OK,01
    });
  }
  else {													// se esiste 
    console.log('Database "Commenti" ready to go!');		// mando in console il messaggio di commenti ready
    db.each('SELECT * from Commenti', function(err, row) {  // query per la selezione di tutti i commenti per stamparli fino a errore o fine db
      if ( row ) {											// se ho un record valido
        console.log('record:', row);						// stampo la riga
      }
    });
  }
});


/*					START APPLICATION                            */
/*****************************************************************/
// http://expressjs.com/en/starter/basic-routing.html
app.get('/', function(request, response) {
  response.sendFile(__dirname + '/views/index.html'); // l'applicazione parte client di test parte da questo punto
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
