FROM popovdenys/wildfly

WORKDIR /opt/jboss/wildfly/

MAINTAINER Popov Denys "popovdenys@gmail.com"

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

EXPOSE 8080 9990

RUN rm -rf standalone/deployments/*
RUN rm -rf standalone/configuration/standalone-full.xml

COPY ./config/wildfly/ modules/system/layers/base/com
COPY ./config/standalone-full.xml standalone/configuration/standalone-full.xml

#USER root
#RUN chown jboss:root modules/system/layers/base/com/**/*
#RUN chown jboss:root modules/system/layers/base/com/mysql/jdbc/*
#RUN chown jboss:root standalone/configuration/standalone-full.xml
#USER jboss

COPY galaxy.war standalone/deployments

CMD ["/opt/jboss/wildfly/bin/standalone.sh",  "-c", "standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
