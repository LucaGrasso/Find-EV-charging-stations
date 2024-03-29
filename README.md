## Progetto Piattaforme Digitali per la Gestione del Territorio ##

## Appello: ##
* Sessione Autunno 2018/2019 

## Studente ##
* [Luca Grasso](https://github.com/LucaGrasso)
* Matricola 294612

## Descrizione generale del servizio ##

Servizio che consente, tramite una app android, all'utente di cercare una colonnina elettrica per ricaricare
la propria auto proponento quella più vicina.

Quindi il progetto si pone come obiettivi primari:
* Ricerca di Colonnina elettrica più vicina con filtro per potenza minima KW impostata da utente.
* Possibilità di inserire un commento per quella colonnina.

## API ##

Per consentire il corretto funzionamento della piattoforma sono presenti delle richieste GET e POST.
I dati sono forniti da [OpenChargeMap](https://openchargemap.org/site) in formato JSON.

Ho creato un WebAPI installata su Glitch che con metodi Get e Post che comunica con OpenChargeMap e filtra
esclusivamente le informazioni che servono al mio Client.

Questo WebAPI realizzato con NodeJS può essere utilizzato da qualsiasi client. Infatti sul server ho anche una
versione HTML/JS che ho utlizzato come Test del Webservice.

Documentazione su swaggerhub [Documentazione OpenApi](https://app.swaggerhub.com/apis-docs/LucaGrasso/FindEV/1.0.0-oas3)
Documentazione su gitHub con esempi di chiamate [SERVER.md](https://github.com/LucaGrasso/Find-EV-charging-stations/blob/master/SERVER.md).

## Relazione ##

Il progetto svolge due funzioni principali:
* La prima consiste nella realizzazione di un applicazione webAPI (GET/POST tramite protocollo HTTP) in NodeJS;
* La seconda invece riguarda l'implementazione di un client Html per i test.
* la terza e ultima parte un semplice client in Java per sistemi Android.

Concettualmente l'intenzione è quella di creare un aiuto per chi cerca una colonnina EV più vicina possibile.
Quindi in base al luogo scelto o alla posizione inviata dall'utente, l'API restituira' la colonnina più vicina.
Analizzato le numerose informazioni che ricevo da ["openchargemap"](https://openchargemap.org/site) attraverso la sua API
ho scelto di filtrare solamente le informazioni di posizione e minimo power KW che necessita l'utente.
Perchè dopo una analisi ho capito che la capità di erogazione determina anche il tipo di attacco necessario e il tempo di ricarica.
Quindi ho creato una mia Api appoggiata su [glitch](http://find-ev-charging-stations.glitch.me/) che svolge tutte le funzioni GET/POST
che necessito.

Come detto in precedenza, mi appoggio alle API di ["openchargemap"](https://openchargemap.org/site), a questo link [guida api openhargemap](https://openchargemap.org/site/develop#api) si trova la guida all'APIche mi restituiranno un json contenete diverse informazioni qua un esempio di una informazione:
* [esempio](https://api.openchargemap.io/v3/poi/?latidune=45&longitude=8&maxresults=1)

Per la parte server come accennato precedentemente, ho utilizzato la piattaforma Glitch come server di appoggio importando la cartella SERVER da git.
In essa è presente l'API server.js messa nel main e le cartelle per una interfaccia client HTML/CSS/JS utilizzata principalmente per testare le risposte Get e Post.

La interfaccia è raggiungibile da qua:
* [glitch](http://find-ev-charging-stations.glitch.me/)
e si presenterà in questo modo

<a><img src='img/01 html-client.JPG' height='500' alt='ScreenShot'/></a>

## Client App Android ##

Con l'utilizzo di Android Studio ho creato un applicazione client per sfruttare meglio il servizio creato.

Qua inserisco le mie cordinate e la min power KW che cerco.
Ho la possibilita di ricavare la mia posizione.
Appena inseriti tutti i dati, l'applicazione abilita il cerca.

<a><img src='img/appClient01.JPG' height='300' alt='ScreenShot'/></a>
<a><img src='img/appClient02.JPG' height='300' alt='ScreenShot'/></a>

viene assegnato un ID all'utente e se viene trovata una colonnina vicina
viene proposta se si vuole vedere la distanza attraverso la mappa di google
oppure mettere un commento per la colonnina utilizzata

<a><img src='img/appClient03.JPG' height='300' alt='ScreenShot'/></a>

attraverso la posizione ricavata posso aprire googlemaps e farmi indicare la strada.

<a><img src='img/appClient03a.JPG' height='300' alt='ScreenShot'/></a>

con l'opzione aggiungi commento, inserisco un commento, apparirà un pop up all'invio.

<a><img src='img/appClient04.JPG' height='300' alt='ScreenShot'/></a>
<a><img src='img/appClient05.JPG' height='300' alt='ScreenShot'/></a>

## Elenco delle licenze degli strumenti utilizzati ##
L'applicazione e servizi sono tutti Open Source / Free to use.

Strumenti:
- [Open Charge Map](https://openchargemap.org/site/about/terms)
- [Glitch](https://glitch.com/legal/)
- [Geolib](https://github.com/manuelbieh/geolib/blob/master/LICENSE)
- [SQLite](https://www.sqlite.org/copyright.html)

 Code Editor
- [Visual Studio Code](https://code.visualstudio.com/license)
- [Android Studio](https://developer.android.com/legal)


## OpenAPI documentazione ##
Il link sottostante collega al sito Swagger con la quale si ha l'opportunità di poter osservare più intuitivamente il comportamento della API, e scaricare la documentazione nel formato OpenApi 3.0.

[Documentazione OpenApi](https://app.swaggerhub.com/apis-docs/LucaGrasso/FindEV/1.0.0-oas3)


Questa applicazione ha uno scopo didattico.
