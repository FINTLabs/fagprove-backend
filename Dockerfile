FROM gradle:8.8-jdk21 as builder
USER root
COPY . .
RUN ./gradlew --no-daemon build

FROM gcr.io/distroless/java21
ENV JAVA_TOOL_OPTIONS -XX:+ExitOnOutOfMemoryError
COPY --from=builder /home/gradle/build/libs/fagprove-backend*.jar /data/app.jar
CMD ["/data/app.jar"]