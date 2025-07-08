FROM openjdk:11-jre-buster

RUN apt-get update && \
    apt-get install -y --no-install-recommends vim chromium && \
    rm -rf /var/lib/apt/lists/*

ENV PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
ENV PLAYWRIGHT_EXECUTABLE_PATH=/usr/bin/chromium

RUN mkdir -p /var/app
COPY ./target/playwright-1.0.0.jar /var/app/playwright.jar

EXPOSE 8080
WORKDIR /var/app

CMD ["java", "-server", "-Xms128m", "-Xmx256m", "-jar", "/var/app/playwright.jar", "--spring.profiles.active=prd"]
