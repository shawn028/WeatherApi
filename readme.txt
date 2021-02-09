1. Property file located at /resource/env.properties, it contains environment information.
2. Feature file located at /feature/calculate.feature.
3. pom.xml is the Maven file contains all plugins and dependencies.
4. log4j.xml is created to support generating logs. 
5. All java files are located under src/test/java
   - org.weather.basic
     ConfigFileReader.java: Contain methods to read environment data from property file.
     Log.java: Contain methods to generate running log.
   - runner 
   	 WeatherApiRunnerTest.java: This is the cucumber runner class contain cucumber running information
   - stepDefinitions
   	 TestWeatherApi.java: Contains all actions to test steps defined in the feature file.  
 6. Running logs stored in /logs.
 7. Running reports stored in /target/cucumber-reports, there are 3 types reports(html/json/xml).
 8. Test run passed in both Eclipse IDE(Run AS Junit Test) and MAVEN command line("mvn test").
