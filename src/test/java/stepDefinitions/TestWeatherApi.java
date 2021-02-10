package stepDefinitions;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.weather.basic.ConfigFileReader;

import org.weather.basic.Log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import cucumber.api.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.testng.asserts.SoftAssert;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.given;

public class TestWeatherApi {
	ConfigFileReader configFileReader;
	SoftAssert softAssert = new SoftAssert();
	
	@Given("^The Open weather station url is running$")
	public void the_Open_weather_station_url_is_running() throws Throwable {
	    // Read baseUrl and key information from property file
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("Checking if the url is running.");
		configFileReader = new ConfigFileReader();
		String ApiBaseUrl = configFileReader.getApiBaseUrl();
		RestAssured.baseURI = ApiBaseUrl;
		Log.endTestCase("Checking if the url is running.- Done, the ApiBaseUrl is:"+ ApiBaseUrl);
	    //throw new PendingException();
	}
	
	@When("^Register a weather station without an API key, body includs \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Register_a_weather_station_without_an_API_key_body_includs(String external_id, String name, String latitude, String longitude, String altitude) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions		
		Log.startTestCase("Register a weather station without an API key starts running.");
		given()
        .header("APPID","")
        .header("Content-Type","application/json")
        .param("external_id", external_id)
        .param("name", name)
        .param("latitude", latitude)
        .param("longitude", longitude)
        .param("altitude", altitude)
        .post("/stations")
        .then().statusCode(401);
		System.out.println("1- " + RestAssured.baseURI);
		Log.endTestCase("Register a weather station without an API key.- Done");
	    //throw new PendingException();
	}
	

	@When("^Register weather station with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Register_weather_station_with(String external_id, String name, String latitude, String longitude, String altitude) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		configFileReader = new ConfigFileReader();
		String KeyType = configFileReader.getKeyType();
		String KeyValue = configFileReader.getKeyValue();
		
		System.out.println(RestAssured.baseURI);
		System.out.println(KeyValue);
		 //RestAssured.baseURI ="http://api.openweathermap.org/data/3.0";
		 final String requestBody = "{\n" +
		            "  \"external_id\": \"" + external_id +"\",\n" +
		            "  \"name\": \"" + name +"\",\n" +
		            "  \"latitude\":" + latitude + ",\n" +
		            "  \"longitude\":" + longitude +",\n" +
		            "  \"altitude\":" + altitude + "\n}";
		 Response response = given()
	                .header("Content-type", "application/json")
	                .and()
	                .body(requestBody)
	                .when()
	                .post("/stations?"+KeyType+"=" + KeyValue)
	                .then()
	                .extract().response();
		 System.out.println(requestBody);
		 softAssert.assertEquals(201, response.statusCode());
		 softAssert.assertEquals(external_id, response.jsonPath().getString("external_id"));
		 softAssert.assertEquals(name, response.jsonPath().getString("name"));
		 softAssert.assertEquals(latitude, response.jsonPath().getString("latitude"));
		 softAssert.assertEquals(longitude, response.jsonPath().getString("longitude"));
		 softAssert.assertEquals(altitude, response.jsonPath().getString("altitude"));
	     
	    //throw new PendingException();
	}
	
	@When("^Query the new created station and response body contain  \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Query_the_new_created_station_and_response_body_contain(String external_id, String name, String latitude, String longitude, String altitude) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		configFileReader = new ConfigFileReader();
		String KeyType = configFileReader.getKeyType();
		String KeyValue = configFileReader.getKeyValue();
		
		Log.startTestCase("Query_the_new_created_station - starts running.");
		Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stations?"+KeyType+"=" + KeyValue + "&external_id="+external_id)
                .then()
                .extract().response();

		Assert.assertEquals(200, response.statusCode());
		
		String strExternal_id = response.jsonPath().getString("external_id");
		Assert.assertTrue(strExternal_id.contains(external_id));
		
		String strName = response.jsonPath().getString("name");
		Assert.assertTrue(strName.contains(name));
		
		String strLatitude = response.jsonPath().getString("latitude");
		Assert.assertTrue(strLatitude.contains(latitude));
		
		String strLongitude = response.jsonPath().getString("longitude");
		Assert.assertTrue(strLongitude.contains(longitude));
		
		String strAltitude = response.jsonPath().getString("altitude");
		Assert.assertTrue(strAltitude.contains(altitude));
		
        softAssert.assertAll();
	    //throw new PendingException();
        Log.startTestCase("Query_the_new_created_station - Done.");
	}
}
