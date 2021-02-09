package stepDefinitions;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.weather.basic.ConfigFileReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.When;

import static io.restassured.RestAssured.given;

public class TestWeatherApi {
	ConfigFileReader configFileReader;
	//private static final String BaseUrl = "http://api.openweathermap.org/data/3.0/stations";
	
	//final String BASE_URL = "http://api.openweathermap.org/data/3.0";
	//final String BASE_PATH = "/data/3.0";
	
	@Given("^The Open weather station url is running$")
	public void the_Open_weather_station_url_is_running() throws Throwable {
	    // Read baseUrl and key information from property file
		configFileReader = new ConfigFileReader();
		String ApiBaseUrl = configFileReader.getApiBaseUrl();
		RestAssured.baseURI = ApiBaseUrl;
	    //throw new PendingException();
	}
	
	@When("^Register a weather station without an API key, body includs \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Register_a_weather_station_without_an_API_key_body_includs(String external_id, String name, String latitude, String longitude, String altitude) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions		
		
		given()
        .header("key","")
        .header("Content-Type","application/json")
        .param("external_id", external_id)
        .param("name", name)
        .param("latitude", latitude)
        .param("longitude", longitude)
        .param("altitude", altitude)
        .post("/stations")
        .then().statusCode(401);
		System.out.println("1- " + RestAssured.baseURI);
	    //throw new PendingException();
	}
	

	@When("^Register weather station with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Register_weather_station_with(String external_id, String name, String latitude, String longitude, String altitude) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//given()
        //.header("key","c418f61b4fb72d7e608bac74d6660b20")
       // .header("Content-Type","application/json")
       // .param("external_id","DEMO_TEST001,")
       // .param("name", "Team Demo Test Station 001,")
       // .param("latitude", "33.33,")
        //.param("longitude", "-122.43,")
       // .param("altitude", "222,")
       // .post("/stations?APPID=c418f61b4fb72d7e608bac74d6660b20")
		//.then().statusCode(201);
		
		//******************************************************************
		RestAssured.baseURI ="http://api.openweathermap.org/data/3.0/stations?appid=c418f61b4fb72d7e608bac74d6660b20";
		 //RequestSpecification request = RestAssured.given();
		 
		 JSONObject  request = new JSONObject();
		 request.put("external_id", "DEMO_TEST001");
		 request.put("name", "Team Demo Test Station 001");
		 request.put("latitude", "33.33");
		 request.put("longitude", "-122.43");
		 request.put("altitude", "222");
		 //request.header("Content-Type", "application/json");
		 
		 given().header("Content-Type","application/json").
			body(request.toJSONString()).
			when().
			post(RestAssured.baseURI).
			then().statusCode(201 );
		 
		 //request.body(request.toJSONString());
		
		 
		 //int statusCode = response.getStatusCode();
		 //System.out.println("status code=" + statusCode);
		// Assert.assertEquals(statusCode, "201");
	    //throw new PendingException();
	}
	
	@When("^Query the new created station and response body contain  \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void Query_the_new_created_station_and_response_body_contain(String external_id, String name, String latitude, String longitude, String altitude) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        String body = response.getBody().asString();
        int status = response.getStatusCode();
        Assert.assertEquals(status, "200");
        String statusLine = response.getStatusLine();
        //Printing the response
        System.out.println("Response Body is "+body);
        System.out.println("Status code is "+status);
        System.out.println("Status line is "+statusLine);
	    //throw new PendingException();
	}

	
	//************************************

	
	
	
}
