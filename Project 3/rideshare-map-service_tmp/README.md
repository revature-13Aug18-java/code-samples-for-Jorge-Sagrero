# Project 3 RideForce maps service

This service handles the following endpoints (see the API documentation in
the gateway service repo for all endpoints and their explanations):

- `/location`
- `/route`
- `/favoritelocations`

## Environment variables

Environment variables are used for sensitive data that should not be exposed
in the public Git repository. They need to be configured to run and test this application. The following is a comprehensive list of all
environment variables that are necessary for proper program execution:

- `JDBC_URL`: the database url
- `JDBC_USERNAME`: the database username
- `JDBC_PASSWORD`: the database password
- `MAPS_API_KEY`: the Google Maps API key

This project was a batch project that was divided into 3 teams with 1 team being the microservice team. The microservice team was split into three groups with my group being the map service team. This was a project handed to us from another batch, and there was no documentation or testing done, so our main focus with this sprint was to perform unit testing and documentation on the microservices and add/resolve features and issues passed on to us from the previous sprint. We managed to get 80% code coverage for our the map microservice and added a feature to save favorite locations based on the user.

# Technologies used: Microservices, Angular, Spring, DevOps, Agile, Java, Maven, Amazon Web Services, Git, 
