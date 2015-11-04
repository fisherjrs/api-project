# api-project
This is a SpringBoot app that provides a small set of RESTful apis for retrieving product data.
The app is built with controllers that receive http requests. The requests are passed to a service layer 
that handles ORM interaction and implements business logic. Since we don't have any business logic you could argue 
that the service layer isn't necessary, but it's there for the sake of best practices. 
Mybatis is used for the ORM layer and MySQL is the database. The data is mapped to a set of model objects that 
are serialized by Jackson into JSON objects and added to the response that's sent back to the client.

**APIs**:
* get product by id
* get list of all products
* get product by category
* get list of categories
* get secure token

**Security**(sort of): 

The app contains a properties file (application.yml in src/main/java) that has a setting to turn security on/off (use-secure-tokens: true/false). If turned on the apis will require a token. The token is retrieved from an api that accepts a requestKey and validates it. If the request key is valid, a valid token is returned to the client to use in subsequent requests such as http://localhost:8080/myretail/v1/getproduct/5555?token=winter ... This isn't intended to be a real security implementation, but a placeholder for how the mechanism might work. 

####Other details:
* Maven is used for dependency management and building.
* Logback is used for logging.
* Jackson for JSON serialization.
* Requires Java8 and MySQL (I'm using v 5.5).


####Running the app:
* You'll need a MySQL instance.
* In src/main/resources/sql folder there's a collections of sql scripts. Run create_products_table.sql and 
create_prices_table.sql.
* Run insert_products_prices.sql to seed the tables with data.
* To connect the app to MySQL edit com.myretail.config.MyRetailConfig.datasource() to use the 
url, username and password for your MySQL instance.
* poc-0.1.0.jar is checked into the root of the project. Drop it on your java classpath 
and run java -jar poc-0.1.0.jar. It "should" run the app. 
* Navigate to **http://localhost:8080/myretail/v1/apis** to get a list of apis to explore.
* Alternatively, you can pull the entire project into SpringToolSuite, open com.myretail.MyRetailApplication
and run it. It "should" work provided your proxy server lets Maven pull in the dependencies.
