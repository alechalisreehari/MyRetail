A simple REST application to get and update product details

### Pre-requisites

For running this application in your local, you must have below tools installed in your server:
1) Java 8 or above
2) Docker ( for running mongo instance)
3) Maven installed

### Instructions to run locally
1) Make sure `Docker` is up and running
2) go to application folder `cd {user}/MyRetailApplication`
3) execute `docker-compose up`. This will start mongoDB instance.
4)go to application folder `cd {user}/MyRetailApplication`
5) execute `mvn clean install`
6) execute `-java -jar myretail-0.0.1-SNAPSHOT.jar`
#### Swagger configuration
http://localhost:9010/swagger

##### Testing the application
1)This API provides the price and description of a product based on the input product id.
```$xslt
    curl -X GET \
      http://localhost:9010/v1/products/13860428 \
      -H 'Accept: */*' \
      -H 'Accept-Encoding: gzip, deflate' \
      -H 'Content-Type: application/json'


2) This API can be used to update the price records for a given product id. The data will be saved in no SQL data source.

```$xslt
    curl -X PUT \
      http://localhost:9010/products/54439661 \
      -H 'Accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
        "value":125,
        "currency_code":"USD"
        }'
