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

# Il Garbage Collector

È il componente che si occupa di *trovare* e *cancellare* gli oggetti in memoria che non sono più referenziati.

Si compone di due fasi principali:
 - **Mark**: identifica gli oggetti in utilizzo e quelli non più e *marchia* questi ultimi;
 - **Sweep**: libera la memoria dagli oggetti marchiati alla fase precedente.

È trasparente all'utilizzatore. Ciò significa che chi sviluppa non deve preoccuparsi del GC, gli basti sapere che questo c'è e di "tanto in tanto" farà il suo lavoro.

Ecco.. questo è vero fino a un certo punto. Il GC ha dei pro, ma anche dei contro, che è bene conoscere:
 - Overhead, tutto questo tenere traccia da parte della JVM non viene gratis;
 - Non si ha controllo su quando il GC inizia le sue fasi. Questo può avere un impatto sull'operatività del nostro applicativo;
 - In determinate situazioni può addirittura portare a un "freeze" dell'applicativo (è anche vero che con gli anni java ha investito tanto nel migliorarlo e i risultati si vedono).

Qualche dettaglio in più: [Baeldung](https://www.baeldung.com/jvm-garbage-collectors), [Uber](https://www.uber.com/en-IT/blog/jvm-tuning-garbage-collection/).

# Tornando a Classi e Oggetti

```java
/*
 * Person is a class.
 * A class describes the behavior of its objects.
 *
 * A class is a static component.
 * In order to make a class "alive" we have to instantiate it.
 * e.g. Person jacopo = new Person(..);
 *
 * In this way a class has its own space in memory and lives autonomously.
 * That means that we can access to it's "visible" interface.
 */
public class Person {

    /*
    * This is a property of this class.
    * It's private that means that this attribute can be access only inside this class and not from the outside by who has the reference of the object.
    *
    * This property is of type: String ; it means that is referring to another object.
    */
    private final String name;
    private final LocalDate birthDate;

    /*
    * This is a: constructor.
    *
    * It can be used in order to instantiate a class into an object.
    */
    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    /*
    * This is a: method.
    * It a functionality that we can call on the instance of the object in order to interact with the object itself.
    *
    * A method has a signature.
    * The signature is composed by (in most cases):
    *
    * <visibility> <return type> <method name> ( <input parameters> )
    *
    * In this case this method is returning a primitive: long.
    *
    * From: https://www.baeldung.com/java-primitives
    *   The eight primitives defined in Java are int, byte, short, long, float, double, boolean and char. These aren’t considered objects and represent raw values.
    *   They’re stored directly on the stack.
    */
    public long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public String getName() {
        return name;
    }

    /*
    * Everything is an object in Java and every object extends java.lang.Object
    * This allow to inherit functionalities from the language.
    *
    * In this case the method toString() is implemented by the java.lang.Object with a default implementation.
    * So, without any explicit action on every Object we can call the method toString() and leverage on the default implementation.
    * Then, if we have the need, we can override the default implementation as below:
    *
    * This is how it works Inheritance.
    */
    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthDate=" + birthDate)
                .toString();
    }
}
```

# Interfacce

> In Java, un'interfaccia è un **contratto** che definisce un insieme di metodi che una classe deve implementare, garantendo così un comportamento specifico senza preoccuparsi dei dettagli di implementazione.

```java
public interface Speaker {
    String speak();
}

public interface Screamer {
    String scream();
}

public class PersonWithVoice implements Speaker, Screamer {

    /*
     * The final keyword.
     * QUESTION: Why using final for the attribute name?
     */
    private final String name;

    public PersonWithVoice(String name) {
        this.name = name;
    }

    @Override
    public String speak() {
        return "Hi, I'm " + this.getName();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String scream() {
        return "HI, I'M " + this.getName() + "!!";
    }
}
```

Notare che una classe può implementare più interfacce.

# Ereditarietà

> In Java, l'ereditarietà è un meccanismo che permette a una nuova classe (sottoclasse o classe derivata) di acquisire i campi e i metodi di una classe esistente (superclasse o classe base), favorendo il riuso del codice e la creazione di una gerarchia "è un tipo di".

```java
public abstract class Animal implements Speaker {

    private final String name;

    /*
    * QUESTION: why protected?
    */
    protected Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Woof Woof";
    }
}

class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Miao miao!";
    }
}
```

# Strutture dati

[Fred Brooks](https://en.wikipedia.org/wiki/Fred_Brooks), in *The Mythical Man-Month* (1975), scrisse:

> Show me your flowchart and conceal your tables, and I shall continue to be mystified. Show me your tables, and I won't usually need your flowchart; it'll be obvious.

Citato da [Eric Raymond](https://it.wikipedia.org/wiki/Eric_Steven_Raymond), in *The Cathedral and the Bazaar* (1999):

> Show me your code and conceal your data structures, and I shall continue to be mystified. Show me your data structures, and I won't usually need your code; it'll be obvious.

(più dettagli [qui](https://news.ycombinator.com/item?id=10293795))


A questi possiamo aggiungere una citazione attribuita a [Linus Torvald](https://en.wikipedia.org/wiki/Linus_Torvalds):

> Bad programmers worry about the code. Good programmers worry about data structures and their relationships.

