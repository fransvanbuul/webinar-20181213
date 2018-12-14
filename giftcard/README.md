# Giftcard demo
As implemented in live coding webinar 13 Dec 2018

### Infrastructure

This application requires two infrastructure items to run. Connection details can
be configured through application.properties. The settings as check-in assume that
we're running everything on localhost with default ports.

The application needs Axon Server. See https://axoniq.io/download for various options of
obtaining Axon Server (Docker image, jar download).

Also, we need Postgres for our read model and token store. The easiest way to get
the required instance and database up and running is through Docker, with the following
command:
```
docker run -it --rm --name postgres -p 5432:5432 -e POSTGRES_USER=giftcard -e POSTGRES_PASSWORD=secret postgres:9.6
```

### Spring profiles

Using [Spring profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html),
you can run this application as a monolith or as a microservices
system. The following 3 profiles are available:
* `command`: Contains the Giftcard aggregate
* `query`: Contains the Giftcard read model
* `client`: Executes a few test commands and a test query

By setting `spring.profiles.active` to `command,query,client`, you'll run the app
as a monolith. Alternatively, you can activate just one profile and create a microservice.






