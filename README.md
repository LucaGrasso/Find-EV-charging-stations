Documento Alpha version

## Progetto Piattaforme Digitali per la Gestione del Territorio ##

### Appello: ###
* Da decidere.

### Alunno: ###
* [Luca Grasso](https://github.com/LucaGrasso)

-----------------------------------------------------

## Descrizione ##
 
Il progetto Colonnina si pone come obiettivi primari:
* Ricerca di Colonnina elettrica più vicina con filtro per potenza minima KW impostata da utente.
* Possibilità di inserire un commento per quella colonnina.

-----------------------------------------------------

## Relazione ##

Il progetto svolge due funzioni principali:
* La prima consiste nella realizzazione di un applicazione webAPI (GET/POST tramite protocollo HTTP) in NodeJS;
* La seconda invece riguarda l'implementazione di un client Html per i test.
* la terza e ultima parte un semplice client in Java per sistemi Android.

<h1>Descrizione API </h1>
In base al luogo scelto o alla posizione inviata dall'utente, l'API restituira' le colonnine.
Il dato di uscita, sara' un file json che conterra' le informazioni richieste.

Per poter utilizzare questo API, mi appoggio alle API di "[openchargemap]"(https://openchargemap.org/site), che mi
restituiranno un json contenete diverse informazioni [esempio](https://api.openchargemap.io/v3/poi/?latidune=45&longitude=8&maxresults=1)


Per la parte server ho utilizzato la piattaforma Glitch con una interfaccia client HTML utilizzata principalmente
per testare le risposte Get e Post.
La interfaccia è raggiungibile da qua:
[glitch](http://find-ev-charging-stations.glitch.me/)

qua metterò le istruzioni di utilizzo con immagini caricate.

<a><img src='Immagini/cerca.png' height='500' alt='ScreenShot'/></a>


## Licenze ##

[Glitch](https://glitch.com/legal/)
[Geolib](https://github.com/manuelbieh/geolib/blob/master/LICENSE)

## altro ##

se avrò altro da aggiungere.
