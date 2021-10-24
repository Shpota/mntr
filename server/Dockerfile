FROM openjdk:11.0.13-slim as builder
COPY build.gradle.kts gradlew settings.gradle.kts gradle.properties /
COPY src src
COPY gradle gradle
RUN ./gradlew installDist

FROM openjdk:11.0.13-jre
RUN mkdir /app
COPY --from=builder /build/install/mntr/ /app/
WORKDIR /app/bin
CMD ["./mntr"]