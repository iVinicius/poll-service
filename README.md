# poll-service

Stack
  - Java 11
  - Springboot
  - Embedded MongoDB (easiest embedded db I found. State is also maintained on app restart)
  - RabbitMQ (smallest config possible for a simple Topic push)
  - REST
  - Swagger
  - Webflux and Reactor (most resource optimizing Java framework up to date and cool functional, reactive programming)
  - Lombok (for less and cleaner code)
  - JUnit, Mockito, Wiremock
  
  
To create a Subject:
curl --location --request POST 'http://localhost:8080/v1/subject' \
--header 'Content-Type: application/json' \
--data-raw '{
    "description": "RS elections are relevant"
}'

To create a Poll: use previous Subject id. You may add a custom duration of minutes
curl --location --request POST 'http://localhost:8080/v1/subject/5faf3ec170e97c170e100a5f/poll?durationMinutes=5'

To vote:
curl --location --request POST 'http://localhost:8080/v1/vote/poll/5faf3ee670e97c170e100a60' \
--header 'Content-Type: application/json' \
--data-raw '{
    "vote": "NO",
    "taxId": 85965254068
}'

Refer to Swagger for more details.
