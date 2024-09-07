Start application
mvn spring-boot:run

Build application
mvn clean package

Docker guideline
Create network:
docker create network product-manager

Start MySQL in product-manager
docker run --network product-manager --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.36-debian

Run your application in devteria-network
docker run --name product-manager --network product-manager -p 8080:8080 -e DBMS_CONNECTION=jdbc:mysql://mysql:3306/student product-manager:0.0.3