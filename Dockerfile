# Stage 1: Build Stage
FROM bellsoft/liberica-runtime-container:jdk-24-stream-musl AS builder

WORKDIR /home/app
ADD . /home/app/spring-boot-security-custom-authprovider
RUN cd spring-boot-security-custom-authprovider && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

# Stage 2: Layer Tool Stage
FROM bellsoft/liberica-runtime-container:jdk-24-cds-slim-musl AS optimizer

WORKDIR /home/app
COPY --from=builder /home/app/spring-boot-security-custom-authprovider/target/*.jar spring-boot-security-custom-authprovider.jar
RUN java -Djarmode=tools -jar spring-boot-security-custom-authprovider.jar extract --layers --launcher

# Stage 3: Final Stage
FROM bellsoft/liberica-runtime-container:jre-24-stream-musl

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
COPY --from=optimizer /home/app/spring-boot-security-custom-authprovider/dependencies/ ./
COPY --from=optimizer /home/app/spring-boot-security-custom-authprovider/spring-boot-loader/ ./
COPY --from=optimizer /home/app/spring-boot-security-custom-authprovider/snapshot-dependencies/ ./
COPY --from=optimizer /home/app/spring-boot-security-custom-authprovider/application/ ./