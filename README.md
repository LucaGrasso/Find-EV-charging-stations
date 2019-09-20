Documento Alpha version

## Progetto Piattaforme Digitali per la Gestione del Territorio ##
-----------------------------------------------------

## Appello: ##
* Sessione Autunno 2018/2019 

## Studente ##
* [Luca Grasso](https://github.com/LucaGrasso)

-----------------------------------------------------

## Descrizione generale del servizio ##
-----------------------------------------------------
Servizio che consente, tramite una app android, all'utente di cercare una colonnina elettrica per ricaricare
la propria auto proponento quella più vicina.

Quindi il progetto si pone come obiettivi primari:
* Ricerca di Colonnina elettrica più vicina con filtro per potenza minima KW impostata da utente.
* Possibilità di inserire un commento per quella colonnina.

## API ##
-----------------------------------------------------
Per consentire il corretto funzionamento della piattoforma sono presenti delle richieste GET e POST.
I dati sono forniti da [OpenChargeMap](https://openchargemap.org/site) in formato JSON.
Ho creato un Webservice installa su Glitch che con metodi Get e Post comunica con OpenChargeMap e mi filtra
esclusivamente le informazioni che servono al mio Client.
Questo Webservice realizzato con NodeJS può essere utilizzato da qualsiasi client. Infatti sul server ho anche una
versione HTML/JS che ho utlizzato come Test del Webservice.
Documentazione API [SERVER.md](\SERVER.MD).

## Relazione ##

Il progetto svolge due funzioni principali:
* La prima consiste nella realizzazione di un applicazione webAPI (GET/POST tramite protocollo HTTP) in NodeJS;
* La seconda invece riguarda l'implementazione di un client Html per i test.
* la terza e ultima parte un semplice client in Java per sistemi Android.

<h1>Descrizione API </h1>
In base al luogo scelto o alla posizione inviata dall'utente, l'API restituira' le colonnine.
Il dato di uscita, sara' un file json che conterra' le informazioni richieste.

Per poter utilizzare questo API, mi appoggio alle API di ["openchargemap"](https://openchargemap.org/site), che mi
restituiranno un json contenete diverse informazioni [esempio](https://api.openchargemap.io/v3/poi/?latidune=45&longitude=8&maxresults=1)


Per la parte server ho utilizzato la piattaforma Glitch con una interfaccia client HTML utilizzata principalmente
per testare le risposte Get e Post.
La interfaccia è raggiungibile da qua:
[glitch](http://find-ev-charging-stations.glitch.me/)

qua metterò le istruzioni di utilizzo con immagini caricate.

<a><img src='Immagini/cerca.png' height='500' alt='ScreenShot'/></a>


## Licenze ##

- [Glitch](https://glitch.com/legal/)
- [Geolib](https://github.com/manuelbieh/geolib/blob/master/LICENSE)

## altro ##

se avrò altro da aggiungere.
