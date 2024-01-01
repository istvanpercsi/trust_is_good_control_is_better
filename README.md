# "Trust is good, control is better"

_More preciselly Vladimir Ilitsch Lenin said "Trust but check"_

## Foreword

In this repo I want to process the **testing**  topic with similar title from [_JavaMagazin (DE) 1.2024_](https://entwickler.de/magazine-ebooks/java-magazin/java-magazin-12024) with some example. 

## First topic is Archtest 

With [Archunit](https://www.archunit.org), architecture of java application can be tested, and some critical code-bug can be avoided. 

A good example is if we want to follow [_Clean Architecture principles_](https://amzn.eu/d/89Oz8Ff) (Hexagonal Architecture), then the architecture of the code can be tested with this Archunit.

### Hexagonal Architecutre

Hexagonal architecture (other words, ports and adapter architecture) is a software design pattern, where the units are loosely coupled, and they are so independent of each other as they can. Business logic (Service) is in the central place, it is independent of the other units of program. The other units can be called as ports or adapters. The adapters can be split to _input_ and _output_ adapters. 

- input interfaces(adapters, ports) can and only can call service layer. Input adapters can be for example: controllers, message queue consumers, etc.
- output adapters can and only can be called from service layer via dependency inversion principle, so we can be sure that the servcie layer stays independent of output adapters. It means interfaces are defined in service layer, but the implementation itself is in adapter. Output adapters can be: DB layer, message queue producers, external clients, etc.

### Strongly typed implementation

Strongly typed design pattern is that, if the starndard language types are not used in the communication between units. For example instead of String a new types are defined to use them as parameters in method calls. For example `Name` and `FamilyName` is defined, so method can be called `method(Name, FamilyName)` instead of `method(String,String)`. So we can control and avoid false handover of parameters in coding time.

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
 |  |-kafka-consumer
 |  |  |-model
 |  |  |  |- *ConsumerMessage.java
 |  |  |- *Consumer.java
 |-output
 |  |-database
 |  |  |-dao
 |  |  |  |-model
 |  |  |  |  |- *DaoData.java
 |  |  |  |-repository
 |  |  |  |  |-entity
 |  |  |  |  |  |- *Entity.java
 |  |  |  |  |- *Repository.java
 |  |  |  |- *DaoImpl.java
 |  |-kafka-producer
 |  |  |-model
 |  |  |  |- *.ProducerMessage.java
 |  |  |- *.ProducerImpl.java
 |  |-http-client
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
#### Package definitions

- `input` - implementations for input port (adapter, interface)
- `input.controller` - API definition
- `input.controller.model` - Request response models for API
- `input.kafka-consumer`

