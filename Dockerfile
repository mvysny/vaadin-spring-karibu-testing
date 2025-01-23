# Allows you to run this app easily as a docker container.
# See README.md for more details.
#
# 1. Build the image with: docker build -t test/vaadin-spring-karibu-testing:latest .
# 2. Run the image with: docker run --rm -ti -p8080:8080 test/vaadin-spring-karibu-testing
#
# Uses Docker Multi-stage builds: https://docs.docker.com/build/building/multi-stage/

# The "Build" stage. Copies the entire project into the container, into the /app/ folder, and builds it.
FROM eclipse-temurin:17 AS BUILD
COPY . /app/
WORKDIR /app/
RUN --mount=type=cache,target=/root/.m2 --mount=type=cache,target=/root/.vaadin ./mvnw clean test package -Pproduction
# At this point, we have the app (executable jar file):  /app/target/my-hilla-app-1.0-SNAPSHOT.jar

# The "Run" stage. Start with a clean image, and copy over just the app itself, omitting gradle, npm and any intermediate build files.
FROM openjdk:21-bookworm
COPY --from=BUILD /app/target/vaadin-spring-karibu-testing-1.0-SNAPSHOT.jar /app/
WORKDIR /app/
EXPOSE 8080
ENTRYPOINT java -jar vaadin-spring-karibu-testing-1.0-SNAPSHOT.jar 8080

