FROM maven:3.6.3-openjdk-11-slim

ADD src /vol/component/src
ADD src/docker/run-service.sh /vol/component/jar/run-service.sh
ADD MvnLocalRepo /vol/component/MvnLocalRepo
ADD suites /vol/component/suites
ADD pom.xml /vol/component/pom.xml

WORKDIR /vol/component/

RUN chmod +x /vol/component/jar/run-service.sh

CMD /vol/component/jar/run-service.sh

CMD ["mvn","compile","exec:java","-Dmaven.repo.local=MvnLocalRepo/"]