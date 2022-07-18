import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("baseUrl");

        /*basePath=/api/
        endpointPostLogin=login
        endpointGetSingleUser=users/2
        createUserPath=users
        */

        RestAssured.basePath = PropertiesCache.getInstance().getProperty("basePath");
        RestAssured.rootPath = "data";
    }
}
