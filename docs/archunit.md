# Archunit

With [Archunit](https://www.archunit.org), architecture of java application can be tested, and some critical code-bug can be avoided. 

A good example is if we want to follow [_Clean Architecture principles_](https://amzn.eu/d/89Oz8Ff) (Hexagonal Architecture), then the architecture of the code can be tested with this Archunit.

## Project Definition

Before the development of the project is started, the structure of the project must be defined. After the strucute is defined, we can write a test for architecture, so structural problems can be avoided, such as class definition at wording place, with wrong name, etc.

### Strongly typed implementation

Strongly typed design pattern is that, if the starndard language types are not used in the communication between units. For example instead of String a new types are defined to use them as parameters in method calls. For example `Name` and `FamilyName` is defined, so method can be called `method(Name, FamilyName)` instead of `method(String,String)`. So we can control and avoid false handover of parameters in coding time.

### Hexagonal Architecutre

Hexagonal architecture (other words, ports and adapter architecture) is a software design pattern, where the units are loosely coupled, and they are so independent of each other as they can. Business logic (Service) is in the central place, it is independent of the other units of program. The other units can be called as ports or adapters. The adapters can be split to _input_ and _output_ adapters.

- input interfaces(adapters, ports) can and only can call service layer. Input adapters can be for example: controllers, message queue consumers, etc.
- output adapters can and only can be called from service layer via dependency inversion principle, so we can be sure that the servcie layer stays independent of output adapters. It means interfaces are defined in service layer, but the implementation itself is in adapter. Output adapters can be: DB layer, message queue producers, external clients, etc.

### Example for a Hexagonal Architecure

In this section you can find an example for hexagonal architecture, of course it is not a mandatory architecture, but in this example you can see which implementations belong to input port, output port, and which one belong to business logic (service):

```text
<project root>
 |-input
 |  |-controller
 |  |  |-model
 |  |  |  |- *Request.java
 |  |  |  |- *Response.java
 |  |  |- *Controller.java
 |  |-kafka_consumer
 |  |  |-model
 |  |  |  |- *ConsumerMessage.java
 |  |  |- *Consumer.java
 |-output
 |  |-database
 |  |  |-dao
 |  |  |  |-model
 |  |  |  |  |- *DaoData.java
 |  |  |  |- *DaoImpl.java
 |  |  |-repository
 |  |  |  |-entity
 |  |  |  |  |- *Entity.java
 |  |  |  |- *Repository.java
 |  |-kafka_producer
 |  |  |-model
 |  |  |  |- *.ProducerMessage.java
 |  |  |- *.ProducerImpl.java
 |  |-http_client
 |  |  |-model
 |  |  |  |- *ClientRequest.java
 |  |  |  |- *ClientResponse.java
 |  |  |- *HttpClientImpl.java
 |-service
 |  |-interface
 |  |  |-input
 |  |  |  |- *Service.java
 |  |  |-output
 |  |  |  |- *Producer.java
 |  |  |  |- *Dao.java
 |  |  |  |- *HttpClient.java
 |  |-model
 |  |  |-literal
 |  |  |  |- *.java
 |  |  |- *Data.java
 |  |- *ServiceImpl.java
```
### Package definitions

- `input` - Implementations for input port (adapter, interface)
- `input.controller` - API definition
- `input.controller.model` - Request response models for API
- `input.kafka_consumer` - Implementations of Kafka consumers
- `input.kafka_consumer.model` - Message models for Kafka costumers
- `output` - Implementations for output port (adapter, interface)
- `output.database` - Implementations of db-connection. Here instead of `database` can be used some unique name, if you have more db connection. 
- `output.database.dao` - Implementations of DAO (Data Access Object). DAO is the _frontend_ for any database doings
- `output.database.repository` - Implementations of repositories which will be called from DAO implementations
- `output.database.repository.entity` - Implementations of database entities.
- `output.kafka_producers` - Implementations of Kafka producers
- `output.kafka_producers.model` - Message models for Kafka producers
- `output.http_client` - Implementations of HTTP clients for external APIs
- `output.http_client.model` - Implementations of request/response models for calling external APIs.
- `service` - Implementations of core business logic
- `service.interface` - Input / Output interfaces
- `service.interface.input` - Input interfaces which defines methods for business logic and is implemented in `service`
- `service.interface.output` - Output interfaces which defines methods for output operations, such as save data into database, send message via kafka queue, etc. 
- `service.model` - Data structures which are used mainly output and in some cases as input for business logic methods.
- `service.model.literal` - Strong types for whole project. It is defined in service level, because this level is independent of other levels.

## Testing

After project structure is defined, an architecture test can be written. In this project we will use Archunit for this purpose

### Adding maven dependency

First maven Archunit maven dependency must be added. After adding this dependency, normal JUnit tests without any special annotation can be written. (`StructureTest.java` in project root)

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit</artifactId>
    <version>1.1.0</version>
    <scope>test</scope>
</dependency>
```

If you want to use special Archunit annotations (such as `@AnalyseClasses` or `@ArchTest`), then the maven dependency for JUnit extension must be added, then the mentioned (and more) annotation can be used (`StuctureExtendedTest.java` in project root)

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>1.1.0</version>
    <scope>test</scope>
</dependency>
```

### Writing a test