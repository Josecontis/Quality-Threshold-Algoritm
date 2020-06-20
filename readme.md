## **1. INTRODUZIONE**

**Descrizione del programma**

QT è un programma Client-Server realizzato per la gestione di informazioni che risiedono in un database secondo l’algoritmo di clustering Quality Threshold.

In particolare, al Server è riservato il compito di gestire le richieste del Client e di eseguire le operazioni di clustering sul database, mentre il Client fornisce, attraverso l’uso di una interfaccia grafica in JavaFx, gli strumenti per consentire all’utente che utilizza il programma di richiedere l’esecuzione delle operazioni previste.

L’insieme di queste operazioni sono:

* Accesso ad una schermata di lavoro tramite credenziali dal Client;

* Estrapolare informazioni dalla base di dati con l’algoritmo di clustering;

* Salvataggio automatico delle informazioni su file dal Client;

* Lettura di informazioni salvate su file dal Client;

* Terminazione delle attività con la conseguente chiusura della finestra;

* Reset di campi inseriti;

* Stampa di tutti i dati riguardanti l’operazione richiesta sul Client;

* Stampa del numero di client collegati in real-time sul Server;

* Stampa delle operazioni richieste dal Client sul Server;

Per eseguire ciascuna delle operazioni previste, il Client, una volta catturate le richieste dall’utente, si rivolge al Server e attende l’esito dell’operazione richiesta.

Client diversi potrebbero accedere simultaneamente agli stessi dati per effettuare le operazioni, pertanto il server una volta avviato rimane in attesa di nuovi client, finché il server non verrà terminato con l’apposito pulsante (Multithreading).

È necessario avviare una sola volta il server per non incombere in eccezioni di tipo non controllate che potrebbero causare un blocco del programma.

## **2. DIAGRAMMI DELLE CLASSI**

**-*default package*-**

![Alt text](/Diagrammi delle classi/QTClientGui.jpg?raw=true "Optional Title")

**Classi utilizzate -*default package*-**

|      Nome       | Descrizione                                                  |
| :-------------: | :----------------------------------------------------------- |
|   MainTestGui   | Classe che  stabilisce la connessione con il server ed elabora le richieste fatte dall’utente  (Client). |
|    LoginGui     | Sottoclasse di Application che  gestisce tutti i pannelli che compongono l’interfaccia con l’utente lato  client (schermata login e schermata delle richieste client). |
|   Application   | Classe che modella tutti gli  strumenti di JavaFx utili.     |
| ServerException | Sottoclasse di Exception che  gestisce tutti i tentativi non riusciti di connessione lato client. |
|    Exception    | Classe che modella tutti i tipi di  eccezioni controllate e non controllate. |

**Interfacce utilizzate** **-*default package*-**

|     Nome     | Descrizione                                                  |
| :----------: | ------------------------------------------------------------ |
| Serializable | Interfaccia  utile per la serializzazione (salvataggio di informazioni su file) delle  richieste del client. |

**-*data package*-**

![image-20200620104955944](C:\Users\TP-LINK\AppData\Roaming\Typora\typora-user-images\image-20200620104955944.png)

**Classi utilizzate -*data package*-**

|         Nome          | Descrizione                                                  |
| :-------------------: | :----------------------------------------------------------- |
|         Data          | Classe per modellare l'insieme di transazioni  contenute nel database. |
|         Item          | Classe astratta che modella un generico item <coppia  attributo-valore>, (Outlook=”Sunny”). |
|       Attribute       | Classe astratta che modella le entità attributo.             |
|         Tuple         | Classe che rappresenta una tupla come sequenza di coppie attributo-valore. |
|    ContinuousItem     | Classe concreta che estende la classe astratta Item e modella una coppia  <Attributo continuo - valore numerico> (Temperature=30.5). |
|  ContinuousAttribute  | Classe che estende la classe astratta Attribute per modellare un attributo  continuo (numerico). |
|     DiscreteItem      | Classe che estende la classe astratta Item e rappresenta una coppia  <Attributo discreto- valore discreto> (Outlook=”Sunny”). |
|   DiscreteAttribute   | Classe che estende la classe astratta Attribute per modellare un attributo Discreto (stringa). |
| EmptyDatasetException | Sottoclasse  di Exception che modella una eccezione controllata da considerare qualora il dataset sia  vuoto. |
|       Exception       | Classe che  modella tutti i tipi di eccezioni controllate e non controllate. |

**Interfacce utilizzate -*data package*-**

|     Nome     | Descrizione                                                  |
| :----------: | ------------------------------------------------------------ |
| Serializable | Interfaccia  utile per la serializzazione (salvataggio di informazioni su file) delle  richieste del client. |
|   Iterable   | Interfaccia utile per scorrere sugli elementi delle  collection. |

**-*database package*-**

![image-20200620104927445](C:\Users\TP-LINK\AppData\Roaming\Typora\typora-user-images\image-20200620104927445.png)

**Classi utilizzate -*database package*-**

|            Nome             | Descrizione                                                  |
| :-------------------------: | ------------------------------------------------------------ |
|          TableData          | Classe che modella  l’insieme di transazioni collezionate in una tabella. |
|         TableSchema         | Classe  che modella lo schema di una tabella nel database di mysql. |
|           Column            | Classe inner  alla classe TableSchema che modella il nome della colonna e il tipo di valore  (Number-String). |
|          DbAccess           | Classe che realizza  l'accesso alla base di dati.            |
|           Example           | Classe per  modellare  una singola transazione letta dalla base di dati. |
|         QUERY_TYPE          | Classe  enumerativa per effettuare operazioni di aggregazione in SQL. |
|     EmptyTypeException      | Sottoclasse  di Exception che gestisce tutti i tentativi non riusciti di connessione lato  client. |
| DatabaseConnectionException | Sottoclasse  di Exception che modella  il fallimento nella connessione al database. |
|      NoValueException       | Sottoclasse  di Exception che modella  l’assenza di un valore all’interno di un resultset. |
|      EmptySetException      | Sottoclasse  di Exception che modella  la restituzione di un resultset vuoto. |
|          Exception          | Classe che  modella tutti i tipi di eccezioni controllate e non controllate. |

**Interfacce utilizzate -*database package*-**

|     Nome     | Descrizione                                                  |
| :----------: | ------------------------------------------------------------ |
| Serializable | Interfaccia utile per la serializzazione  (salvataggio di informazioni su file) delle richieste del client. |
|  Comparable  | Interfaccia utile per soddisfare una  relazione d’ordine tra valori. |

**-*mining package*-**

![image-20200620104852732](C:\Users\TP-LINK\AppData\Roaming\Typora\typora-user-images\image-20200620104852732.png)

**Classi utilizzate -*mining package*-**

|           Nome            | Descrizione                                                  |
| :-----------------------: | ------------------------------------------------------------ |
|          QTMiner          | Classe che include l'implementazione dell’algoritmo QT (metodo compute) e  serializzazione e deserializzazione su file delle informazioni. |
|          Cluster          | Classe che modella un cluster.                               |
|        ClusterSet         | Classe che rappresenta un insieme di cluster (determinati da QT). |
| ClusteringRadiusException | Sottoclasse  di Exception che modella una eccezione controllata da considerare qualora l'algoritmo di  clustering generi un solo cluster. |
|         Exception         | Classe che  modella tutti i tipi di eccezioni controllate e non controllate. |

**Interfacce utilizzate -*mining package*-**

|        Nome         | Descrizione                                                  |
| :-----------------: | ------------------------------------------------------------ |
|    Serializable     | Interfaccia utile per la serializzazione  (salvataggio di informazioni su file) delle richieste del client. |
| Comparable<Cluster> | Interfaccia utile per soddisfare una  relazione d’ordine tra valori di  tipo Cluster e sottotipi. |
|  Iterable<Cluster>  | Interfaccia utile per scorrere sugli elementi delle collection  con un dominio di valori di tipo Cluster e sottotipi. |

**-*server package*-**

![image-20200620104809699](C:\Users\TP-LINK\AppData\Roaming\Typora\typora-user-images\image-20200620104809699.png)

**Classi utilizzate -*server package*-**

|    Nome     | Descrizione                                                  |
| :---------: | ------------------------------------------------------------ |
|  ServerGui  | Sottoclasse di Application che gestisce  tutti i pannelli che compongono l’interfaccia lato Server, le connessioni e  le richieste dei clients. |
| Application | Classe che modella tutti gli strumenti di  JavaFx utili.     |

**Interfacce utilizzate -*server package*-**

|     Nome     | Descrizione                                                  |
| :----------: | ------------------------------------------------------------ |
| Serializable | Interfaccia utile per la serializzazione  (salvataggio di informazioni su file) delle richieste del client. |

## **3. SERVIZI UTILIZZATI**

**Mysql** 

Grazie all’utilizzo di mysql command line ho potuto creare un database ed una tabella al suo interno, che mi ha permesso di effettuare tutte le operazioni di clustering richieste. 

Ho utilizzato un connettore mysql che ho aggiunto nel buildpath del progetto, per permettere l’accesso al database creato precedentemente, tenendo conto anche, del driver di connessione.

**JavaFx**

Prima di tutto ho aggiunto al buildpath di entrambi i progetti la libreria javafx che contiene tutti i metodi da utilizzare per realizzare l’interfaccia grafica.

Ho utilizzato il programma Scene Builder per costruire una interfaccia grafica ad hoc per il progetto, generando un file di tipo FXML (presente in entrambi i progetti nel package XML) che racchiude tutte le funzioni della GUI creata. 

Ho utilizzato alcune istruzioni indipendenti dal file fxml e da scene builder, che permettono a priori, di avviare una schermata quando si avvia una applicazione che sia client/server.

Il package img in entrambi i progetti contiene le immagini utilizzate nella GUI.

**Javadoc**

Per maggiori informazioni su metodi o attributi di tutte le classi dell’intero progetto consultare la cartella javadoc presente nel buildpath di ciascun progetto.
