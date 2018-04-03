
# Spring-cloud Microservices with SWEAGLE demo

This is an example spring cloud project consisting of the following microservices:
- [config](https://github.com/sweagleExpert/microServiceExample/tree/master/config): the "Sweagle-enabled" Config Server
- [gateway](https://github.com/sweagleExpert/microServiceExample/tree/master/gateway): the API Gateway (Zuul)
- [registry](https://github.com/sweagleExpert/microServiceExample/tree/master/registry): the Service Discovery Server (Eureka)
- [weather-service](https://github.com/sweagleExpert/microServiceExample/tree/master/weather-service): an example service with a REST endpoint for 
demo purposes

The microservices: *gateway*, *registry* and *weather-service* do not hold locally any properties except the URL of the *config* service.
The *config* service is the one which is actually communicating with Sweagle to retrieve and provide the properties of all 3 services depending on 
the profile enabled. The `application.yaml` file of the *config* service contains the necessary settings for interfacing with Sweagle.

# Pre-requisites

- Java 8 and Maven
- Active Sweagle account
- Upload configuration data in Sweagle for each and every microservice and environment profile (e.g. default, development, production etc.). 
Following are instructions/screenshots for profile: 'default'

**Key names & values should to be inherited in every node in the dimension** 

>
> | Key | Value | 
> | ------ | :------: |
> | eureka.client.serviceUrl.defaultZone  | http://127.0.0.1:8761/eureka/ |
> | eureka.instance.prefer-ip-address     | true |


![](https://github.com/sweagleExpert/microServiceExample/blob/master/assets/data-model.png?raw=true)



**gateway**
> | Key | Value | 
> | ------ | :------: |
> |eureka.instance.prefer-ip-address | true|
> | ribbon.ConnectTimeout  | 20000 |
> | ribbon.ReadTimeout | 20000 | 
> | server.port  | 4000 |
> | eureka.client.serviceUrl.defaultZone  | http://127.0.0.1:8761/eureka/ |
> | zuul.host.socket-timeout-millis  | 20000 |
> | zuul.ignoredServices  | '*' |
> | zuul.routes.weather-service.path  | /weather/** |
> | zuul.routes.weather-service.sensitiveHeaders |  | 
> | zuul.routes.weather-service.serviceId  | weather-service |
> | zuul.routes.weather-service.stripPrefix  | false |


**registry** 

> | Key | Value | 
> | ------ | :------: |
> |eureka.client.fetch-registry  | false |
> | eureka.client.register-with-eureka  | false |
> | eureka.client.server.waitTimeInMsWhenSyncEmpty | 0 | 
> | eureka.client.serviceUrl.defaultZone  | http://127.0.0.1:8761/eureka/ |
> | eureka.instance.prefer-ip-address  | true |
> | server.port  | 8761 |

**weather-service**
> | Key | Value | 
> | ------ | :------: |
> |default-city | Brussels
> |eureka.client.serviceUrl.defaultZone | http://127.0.0.1:8761/eureka/ |
> |eureka.instance.prefer-ip-address	| true |
> |server.context-path	| /weather|
> |server.port	| 10600 |

- Create and publish metadata parser taking as parameters the microservice application's name and the environment profile's name
![](https://github.com/sweagleExpert/microServiceExample/blob/master/assets/exporter-1.png?raw=true)

![](https://github.com/sweagleExpert/microServiceExample/blob/master/assets/exporter-2.png?raw=true)

- Check out the 1.0.0-SNAPSHOT version of [spring-cloud-config-server-sweagle](https://github.com/sweagleExpert/envRepository)
- Build and install the artifact into your maven repository. You may find instructions [here](https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html)


# Running the demo

Checkout the latest version of [microServiceExample](https://github.com/sweagleExpert/microServiceExample) and build the project using maven:
```aidl
cd <dir>
mvn clean package
```
Run each microservice in different console/terminals. The recommended order is the following:
#### 1. Config Service
     cd <dir>/config/
     mvn spring-boot:run
     
#### 2. Registry (Service Discovery with Eureka)
     cd <dir>/registry/
     mvn spring-boot:run     

#### 3. API Gateway (Zuul)
     cd <dir>/gateway/
     mvn spring-boot:run    
          
### Running the Functional services ###
#### 1. Weather Service
     cd <dir>/weather-service/
     mvn spring-boot:run
     
### Eureka Dashboard ###
Once you have started all the services, check [Eureka dashboard](http://localhost:8761) 

### Tests ###

- Perform an HTTP GET at: http://localhost:4000/weather/default and view the String response
- Go in Sweagle, start a data change-set and modify the property `defaultCity` for the **weather-service**. Approve and store.
- Stop/start the **weather-service**
- Perform an HTTP GET at: http://localhost:4000/weather/default. Notice that the String response has changed.

> NOTE: in case we have properties in Beans annotated with `@RefreshScope`, then no re-starts are necessary. For more information check 
[Getting Started · Centralized Configuration - Spring](https://spring.io/guides/gs/centralized-configuration/). This important feature is fully 
supported by [spring-cloud-config-server-sweagle](https://github.com/sweagleExpert/envRepository)

# References
- [spring-cloud-config-server-sweagle](https://github.com/sweagleExpert/envRepository)
- [sweagle](https://www.sweagle.com/)
