#Create a Nginx Container
FROM ubuntu
MAINTAINER demousr@gmail.com
RUN apt-get update \
    && apt-get install -y nginx \
    && apt-get clean \
