# Spring-cloud Microservices with SWEAGLE demo

This is an example spring cloud project consisting of the following microservices:
- config: the "Sweagle-enabled" Config Server
- gateway: the API Gateway (Zuul)
- registry: the Service Discovery Server (Eureka)
- weather-service: an example service with a REST endpoint for demo purposes

The microservices: gateway, registry and weather-service do not hold locally any properties except the URL of the Config server.
The Config server is the one who is actually communicating with Sweagle to retrieve and provide the properties of all 3 services depending on the 
profile enabled

# Pre-requisites

- Active Sweagle account
- Uploaded configuration data in Sweagle for each and every microservice and environment profile (e.g. default, development, production etc.)
- Published metadata parser taking as parameters the microservice application's name and the environment profile's name
- Check out the 1.0.0-SNAPSHOT version of spring-cloud-config-server-sweagle
- Build and install the artifact into your maven repository


# Running the demo

Run each service in different console/terminal. The recommended order is the following:
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

# References
[spring-cloud-config-server-sweagle](https://github.com/sweagleExpert/envRepository)
[sweagle](https://www.sweagle.com/)
