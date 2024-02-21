import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestCases {

        private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
        private static final String USERS_ENDPOINT = "/users";
        private static final String POSTS_ENDPOINT = "/posts";
        private static int USER_ID;


        @BeforeClass
        public static void setup() {
            RestAssured.baseURI = BASE_URL;
            USER_ID = Requests.getRandomNumber();
        }

        @Test
        public void getUserEmail() {

            Response r = given().pathParam("userId", USER_ID).get(USERS_ENDPOINT+"/{userId}");
            JsonPath responseBody = r.getBody().jsonPath();
            System.out.println(responseBody.get("email").toString());
        }

        @Test
        public void getUserPosts() {
            Response r =given().queryParam("userId",USER_ID ).get(POSTS_ENDPOINT);
            Assert.assertTrue(checkUserId(r.jsonPath()));
            Assert.assertTrue(checkPostId(r.jsonPath()));
            r.prettyPrint();
        }


        public static boolean checkUserId(JsonPath jsonPath)
        {
            boolean flag =true;
            for (Object s:jsonPath.getList("userId"))
                if (!s.equals(USER_ID)) {
                    flag = false;
                    break;
                }


            System.out.println(flag);
            return flag;

        }





    public static boolean checkPostId(JsonPath jsonPath)
        {

            boolean flag =true;
            for (Object s:jsonPath.getList("id"))
                if (!((int)s >= 1 && (int)s <= 100)) {
                    flag = false;
                    break;
                }
            System.out.println(flag);
            return flag;
        }



    }





