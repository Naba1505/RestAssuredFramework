package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUsers(String userid, String username, String firstname, String lastname, String email,
			String password, String phone) {

		User u = new User();

		u.setId(Integer.parseInt(userid));
		u.setUsername(username);
		u.setFirstName(firstname);
		u.setLastName(lastname);
		u.setEmail(email);
		u.setPassword(password);
		u.setPhone(phone);

		Response res = UserEndPoints.createuser(u);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUsers(String username) {

		Response deleteRes = UserEndPoints.deleteuser(username);
		deleteRes.then().log().all();
		Assert.assertEquals(deleteRes.getStatusCode(), 200);
	}
}
