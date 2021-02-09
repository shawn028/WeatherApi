package org.weather.basic;


import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiTools {
	public static Response post(String apiPath, String json) {
		// 	Start POST request
		Response response = given().
           contentType("application/json;charset=UTF-8").
           headers("header1", "value1").
           cookies("cookies1", "value1").
           body(json).
           when().log().all().post(apiPath.trim());
		 Log.info("status code is:" + response.statusCode());
		 Log.info("reponse:");
		 response.getBody().prettyPrint();
		 return response;
	}

	public static Response get(String apiPath) {
		// Start GET request
		Response response = given().
			contentType("application/json;charset=UTF-8").
			headers("headers1", "value1").
			cookie("cookie1", "value1").
			when().log().all().get(apiPath.trim());
		Log.info("status code is:" + response.statusCode());
   		Log.info("reponse:");
   		response.getBody().prettyPrint();
   		return response;
	}

	public static String getJsonPathValue(Response response, String jsonPath) {
		String reponseJson = String.valueOf(response.jsonPath().get(jsonPath));
//      String jsonValue = String.valueOf(from(reponseJson).get(jsonPath));
		return reponseJson;
	}
}
