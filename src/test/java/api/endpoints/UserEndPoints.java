  package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createuser(User payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;

	}

	public static Response getuser(String UserName) {

		Response response = given().pathParam("username", UserName).when().get(Routes.get_url);

		return response;

	}

	public static Response updateuser(String UserName, User payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", UserName).when().put(Routes.update_url);

		return response;

	}

	public static Response deleteuser(String UserName) {

		Response response = given().pathParam("username", UserName).when().delete(Routes.delete_url);

		return response;

	}

}
