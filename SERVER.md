## Descrizione SERVER.JS ##

Ho creato una web service per filtrare le informazioni da OpenChargeMap con metodi GET e POST

### Richieste GET ###

1) Chiamata per cercare la colonnina più vicina data una posizione e un determinato valore della colonnina
Parametri Get:
 - Latitudine
 - Longitudine
 - MinPower
 
 ```javascript
app.get('/trova-colonnine', function (req, res)
```
Esempio get completo http://find-ev-charging-stations.glitch.me/trova-colonnine/?longitude=8&latitude=44&minpowerkw=2

2) Chiamata per reperire tutti i commenti.
 - rows del database
 
  ```javascript
app.get('/getCommenti', function(request, response)
```

Esempio get http://find-ev-charging-stations.glitch.me/getCommenti

