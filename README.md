# Java è il ecosistema sul quale fare software a oggi

*Descrivimi con una frase java come linguaggio di programmazione:* 

Gemini: 

> Java è un linguaggio di programmazione **orientato agli oggetti**, **indipendente dalla piattaforma** (grazie alla JVM)
e **largamente utilizzato** per lo sviluppo di applicazioni enterprise, mobili (Android), web e desktop.

Copilot:

> Java è come un architetto rigoroso: organizzato, tipizzato e orientato agli oggetti, costruisce strutture solide e scalabili con precisione e disciplina.

Wikipedia:

> In informatica Java è un linguaggio di programmazione ad alto livello, orientato agli oggetti e a tipizzazione statica, che si appoggia sull'omonima piattaforma software di esecuzione, specificamente progettato per essere il più possibile indipendente dalla piattaforma hardware di esecuzione (tramite compilazione in bytecode prima e interpretazione poi da parte di una JVM) 


![](./docs/img/objects-everywhere.jpg)

### Orientato agli oggetti

In Java, un oggetto è un'**istanza concreta di una classe**, che possiede un suo **stato** (i valori dei suoi attributi) e un suo **comportamento** (le azioni che può eseguire tramite i suoi metodi).

Tutto in java è un oggetto… o quasi!

### Tipizzato

Quando si dice che Java è un linguaggio **tipizzato**, si intende che ogni elemento (variabile, parametro, valore di ritorno) deve avere un **tipo di dato esplicitamente dichiarato** e che il compilatore **verifica la compatibilità dei tipi** in fase di compilazione, garantendo maggiore robustezza e prevedibilità del codice.

### Compilato

Nelle frasi precedenti non viene dato a parer mio abbastanza spazio al fatto che java sia un linguaggio *compilato* e allo stesso tempo *interpretato*

> Se io dicessi che java è un linguaggio compilato e allo stesso tempo interpretato. Sto dicendo una cosa sbagliata?
>
> Java è sia compilato che interpretato. Il codice sorgente Java (.java) viene prima compilato in un formato intermedio chiamato bytecode (.class) dal compilatore Java (javac). Successivamente, questo bytecode viene interpretato ed eseguito dalla Java Virtual Machine (JVM) su qualsiasi piattaforma. Questa architettura ibrida è ciò che garantisce la sua celebre portabilità ("write once, run anywhere")

![](./docs/img/JDK.png)

### Ad alto livello

> Java è progettato per essere facilmente leggibile e scrivibile dagli esseri umani, astraendo e gestendo automaticamente dettagli complessi dell'hardware e della memoria, come la gestione della memoria

Quindi tenta di distanziarsi dal come ragiona una macchina al fine di liberare il programmatore da problematiche. In questa frase si fa riferimento alla *gestione della memoria* in quanto Java nasce in anni in cui si veniva dal C (i componenti di Java stesso sono scritti in C) e il C richiede che sia lo sviluppatore a decidere quando un'area di memoria non è più utilizzata. Mostrando il fianco a tutta una serie di problematiche.
Java cerca di risolvere questo problema (riuscendoci in gran parte ma introducendo altre tipologie di difficoltà da gestire) liberando lo sviluppatore dall'onere di liberare lo spazio in memoria esplicitamente lasciando che sia il 
Garbage Collector a occuparsene.


# In questo corso

L'idea dietro a questo corso è di appoggiarsi sui seguenti argomenti per poi avviare una discussione che può portarci ovunque.

### Dentro Java

- Hello World
- Primi passi
- Concetti di OOP
- Strutture dati
- dopo java 8

### Book store API

- HTTP e Rest API
- Autenticazione e autorizzazione
- Quarkus
- Dependency injection
- Interagire con un database
- Testing
- Prassi per collaborare a un progetto con più contributori
- OWASP Top 10

### Se riusciamo ci butteremo in mezzo un po' di:

- Optional
- Stream
- Riferimenti alla programmazione funzionale
- DevOps

# Dentro Java

### Hello World
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Lo possiamo compilare posizionandoci nella sua stessa cartella e poi lanciando il comando: 

> javac HelloWorld.java

Per poi eseguirlo con il comando:

> java HelloWorld

javac = jdk , java = jre

### Classi e Oggetti

> È un modello per creare **oggetti**, definendo le caratteristiche (attributi o proprietà) e i comportamenti (metodi) comuni a tutti gli oggetti di quel tipo.

Cos'è un oggetto a questo punto: 

> È un'istanza concreta e autonoma di una classe, che possiede uno stato specifico (i valori dei suoi attributi) e può eseguire azioni (tramite i suoi metodi).

```java
class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class PersonBuilder {
    private static Person buildPerson(int id, String name) {
        return new Person(id, name);
    }

    public static void main(String[] args) {
        int id = 23;
        String name = "John";
        Person person = null;
        person = buildPerson(id, name);
    }
}
```

Da: https://www.baeldung.com/java-stack-heap

![](./docs/img/java-heap-stack-diagram.png)

> Nel contesto della gestione della memoria in Java, lo Stack è un'area di memoria utilizzata per memorizzare le chiamate ai metodi e le variabili locali primitive (seguendo la logica LIFO - Last-In, First-Out), mentre l'Heap è un'area di memoria condivisa e più grande dove vengono creati tutti gli oggetti e le variabili d'istanza.

### Stack
 - piccolo
 - contiene primitive e riferimenti a oggetti
 - uno spazio qui dentro viene subito de-allocato al termine del suo utilizzo

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at MyStackTraceExample.methodC(MyStackTraceExample.java:18)
	at MyStackTraceExample.methodB(MyStackTraceExample.java:13)
	at MyStackTraceExample.methodA(MyStackTraceExample.java:9)
	at MyStackTraceExample.main(MyStackTraceExample.java:5)
```

### Heap
 - grande.. grandissimo (ma non infinito)
 - se finisce: OutOfMemoryException
 - più lento
 - pericoloso.. accessi concorrenti
 - ripulito dal GC