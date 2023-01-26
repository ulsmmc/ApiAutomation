package api.test;

import static org.testng.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	// Biz burada sadece user model icin test case yazdik. Pet icin ve digerleri icinde PetTests 
	// Olarak acilabilir.
	
	Faker faker;
	User userPayload;  // Bunu olusturduk cunku burada fakerla ne uretirsek gidip onu User.Java ya yazacak
	public Logger logger;
	
	@BeforeClass  // It will start first
	public void setUp() {
		faker = new Faker();
		userPayload = new User(); // Both should be done paralelly
		
		userPayload.setId(faker.idNumber().hashCode());  // Burada fake id uretip set ile User.java ya gonderiryor
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5 ,10)); //5 ve 10 u anlamadim
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	
		//Step9 daki log4j2 da asagidaki kodu yaziyoruz log kaydetmesi icin.
		logger= LogManager.getLogger(this.getClass());
				
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("***************Creating User*******************");
		Response response = userEndPoints.createUSer(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("***************Reading User*******************");
		Response response = userEndPoints.readUser(this.userPayload.getUsername()); // Burada iceriye attigimiz deger yukaridaki uretilen deger
		response.then().log().all();
		Assert.assertEquals(response.statusCode() , 200); 		
		logger.info("***************User info diplayed*******************");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("***************Updating User*******************");
		// Update data using payload setupdata kisminda urettigimizi burada tekrar calistirirsak
		// Tekrar uretmis olacak ve tekrar User.java classi na ekleyecek update kismi degisiklikliler
		// Bu sekilde yapiliyor. Eger istedigin spesifik bir isim var ise faker kullanmadan set de edebilrisin.
		
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = userEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************Updated  User*******************");
		
		//Checking data after the update
		Response responseAfterUpdate = userEndPoints.readUser(this.userPayload.getUsername()); // Burada iceriye attigimiz deger yukaridaki uretilen deger
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.statusCode() , 200); 
		
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("***************Deleting User*******************");
		Response responseafterdel = userEndPoints.deleteUser(this.userPayload.getUsername()); // Burayi unutma.
		Assert.assertEquals(responseafterdel.getStatusCode(), 200);
		responseafterdel.then().log().all();
		logger.info("***************Deleted User*******************");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
