## complitech-api ##

#### This application is a test task from [complitech](https://www.complitech.org/).

There's support for the following features:

* CRUD operations for user.
* Authorization using JWT.
* WebSocket for STOMP messaging (JSON) with a client on SockJS.
* Scheduled, to check the application time off.
* Client method with a request to create a user using RestTemplate.
* Method to delete users with IDs within the range sent by the client.

## How to run this?
```bash

$ Create a postgresql database:
    name: complitech_api_db
    username: postgres
    password: postgres
$ git clone https://github.com/EvgenyLeshevich/complitech-api.git
$ cd complitech-api
$ ./gradlew bootRun

//dockerize

$ ./gradlew build
$ docker-compose up -d
```


## Swagger Docs ##

The project has been configured with a Swagger docket that exposes the APIs with the schema

Accessible at `http://localhost:8080/swagger-ui/index.html` once the app is running.

![image](https://user-images.githubusercontent.com/73518823/174857217-cfb0fdf7-e273-4677-9fa7-fcbbf9b0c526.png)
