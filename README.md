## Progetto Piattaforme Digitali per la Gestione del Territorio ##

## Appello: ##
* Sessione Autunno 2018/2019 

## Studente ##
* [Luca Grasso](https://github.com/LucaGrasso)

## Descrizione generale del servizio ##

Servizio che consente, tramite una app android, all'utente di cercare una colonnina elettrica per ricaricare
la propria auto proponento quella più vicina.

Quindi il progetto si pone come obiettivi primari:
* Ricerca di Colonnina elettrica più vicina con filtro per potenza minima KW impostata da utente.
* Possibilità di inserire un commento per quella colonnina.

## API ##

Per consentire il corretto funzionamento della piattoforma sono presenti delle richieste GET e POST.
I dati sono forniti da [OpenChargeMap](https://openchargemap.org/site) in formato JSON.

Ho creato un WebAPI installa su Glitch che con metodi Get e Post comunica con OpenChargeMap e mi filtra
esclusivamente le informazioni che servono al mio Client.

Questo WebseAPI realizzato con NodeJS può essere utilizzato da qualsiasi client. Infatti sul server ho anche una
versione HTML/JS che ho utlizzato come Test del Webservice.

Documentazione API [SERVER.md](https://github.com/LucaGrasso/Find-EV-charging-stations/blob/master/SERVER.md).

## Relazione ##

Il progetto svolge due funzioni principali:
* La prima consiste nella realizzazione di un applicazione webAPI (GET/POST tramite protocollo HTTP) in NodeJS;
* La seconda invece riguarda l'implementazione di un client Html per i test.
* la terza e ultima parte un semplice client in Java per sistemi Android.

Concettualmente l'intenzione è quella di creare un aiuto per chi cerca una colonnina EV più vicina possibile.
Quindi in base al luogo scelto o alla posizione inviata dall'utente, l'API restituira' la colonnina più vicina.
Analizzato le numeto informazioni che ricevo da ["openchargemap"](https://openchargemap.org/site) attraverso la sua API
ho scelto di filtrare solamente le informazioni di posizione e minimo power KW che necessita l'utente.
Perchè dopo una analisi ho capito che la capità di erogazione determina anche il tipo di attacco necessario e il tempo di ricarica.
Quindi ho creato una mia api appoggiata su [glitch](http://find-ev-charging-stations.glitch.me/) che svolge tutte le funzioni GET/POST
che necessito.

Come detto in precedenza, mi appoggio alle API di ["openchargemap"](https://openchargemap.org/site), a questo link [guida api openhargemap](https://openchargemap.org/site/develop#api) si trova la guida all'APIche mi restituiranno un json contenete diverse informazioni qua un esempio di una informazione:
* [esempio](https://api.openchargemap.io/v3/poi/?latidune=45&longitude=8&maxresults=1)

Per la parte server come accennato precedentemente, ho utilizzato la piattaforma Glitch come server di appoggio importando la cartella SERVER da git.
In esse è presente l'API server.js messa nel main e le cartelle per una interfaccia client HTML/CSS/JS utilizzata principalmente per testare le risposte Get e Post.

La interfaccia è raggiungibile da qua:
* [glitch](http://find-ev-charging-stations.glitch.me/)
e si presenterà in questo modo

<a><img src='img/01 html-client.JPG' height='500' alt='ScreenShot'/></a>

## Elenco delle licenze degli strumenti utilizzati##
L'applicazione e servizi sono tutti Open Source / Free to use.

Strumenti:
- [Open Charge Map](https://openchargemap.org/site/about/terms)
- [Glitch](https://glitch.com/legal/)
- [Geolib](https://github.com/manuelbieh/geolib/blob/master/LICENSE)
- [SQLite](https://www.sqlite.org/copyright.html)

 Code Editor
- [Visual Studio Code](https://code.visualstudio.com/license)
- [Android Studio](https://developer.android.com/legal)

Questa applicazione ha uno scopo didattico.