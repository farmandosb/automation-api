import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetRequestTest extends BaseTest {

    @Test
    public static void getResponseBody() {
        given()
                .queryParam("page", 2)
                .queryParam("per_page", 2)
                .when()
                .get("/users")
                .then()
                .body("size()", equalTo(2));
    }

    @Test
    public static void getResponseStatus() {
        given()
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public static void getContentType() {
        given()
                .get("https://reqres.in/api/users")
                .then()
                .contentType("application/json");
    }

    @Test
    public static void getUsers() {

        String productsJsonPath = get("https://reqres.in/api/users").asString();
        List<Map<String, Object>> users = from(productsJsonPath).getJsonObject("data");

        Assert.assertTrue(users.stream()
                .filter(u -> u.get("last_name").toString().contains("W"))
                .anyMatch(u -> u.get("first_name").equals("Emma")));
    }

    @Test
    public static void getSingleUserById() {
        UserDTO userDTO = get("https://reqres.in/api/users/2").as(UserDTO.class);
        Assert.assertNotNull(userDTO);
    }
}
