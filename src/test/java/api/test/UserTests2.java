package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	public static Logger logger;

	@BeforeClass
	public void setup() {

		logger = LogManager.getLogger(UserTests2.class);

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 8));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.debug("***** Starting testPostUser *****");
		logger.info("***** User Created *****");

		Response res = UserEndPoints2.createuser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.debug("***** Completed testPostUser *****");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.debug("***** Starting testGetUserByName *****");
		logger.info("***** Get Users *****");

		Response res = UserEndPoints2.getuser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
		logger.debug("***** Completed testGetUserByName *****");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.debug("***** Starting testUpdateUserByName *****");
		logger.info("***** User Updated *****");

		// Data should be updated for below parameters
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response res = UserEndPoints2.updateuser(this.userPayload.getUsername(), userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.debug("***** Completed testUpdateUserByName *****");
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.debug("***** Starting testDeleteUserByName *****");
		logger.info("***** User Deleted *****");

		Response res = UserEndPoints2.deleteuser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
		logger.debug("***** Completed testDeleteUserByName *****");
	}

}
