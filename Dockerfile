FROM maven:3.6.3-openjdk-11-slim

ADD src /vol/component/src
ADD MvnLocalRepo /vol/component/MvnLocalRepo
ADD suites /vol/component/suites
ADD pom.xml /vol/component/pom.xml

WORKDIR /vol/component/

CMD ["mvn","compile","exec:java","-Dmaven.repo.local=MvnLocalRepo/"]