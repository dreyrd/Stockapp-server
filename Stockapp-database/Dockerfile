FROM mysql:8.0.43

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=stockapp

COPY stockapp_database.tar /docker-entrypoint-initdb.d/dump.sql