package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	
	
	@Test(priority = 1, dataProvider="Data" , dataProviderClass = DataProviders.class)
	public void testPostUser(String userID , String userName, String fname, String Iname,String useremail,String pwd, String ph) { //parametlerler exceldeki ise same order olmali
	
		
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(Iname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=userEndPoints.createUSer(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
	
		//CREATED MULTIPLE USERS
	}
	@Test(priority =2 ,dataProvider = "UserNames", dataProviderClass= DataProviders.class)   // data pro icindeki username olarak adlandirilani cek
	public void testDeleteUserByName(String userName) {
		Response response =userEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		// DELETE MULTIPLE USER
	}
	
	
	
	
	
}
