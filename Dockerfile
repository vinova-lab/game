# Use a standard JDK image
FROM eclipse-temurin:17-jdk


WORKDIR /app


# Copy project files
COPY . /app


# Compile all java sources into build/
RUN mkdir -p /app/build \
&& find . -name "*.java" > /tmp/sources.txt \
&& javac -d /app/build @/tmp/sources.txt


# Copy run script and make it executable
COPY run-game.sh /app/run-game.sh
RUN chmod +x /app/run-game.sh


# Default game (can be overridden with -e GAME=snake)
ENV GAME=brickbreaker


ENTRYPOINT ["/app/run-game.sh"]
