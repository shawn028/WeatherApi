package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@CucumberOptions(features=("features"),glue=("stepDefinitions"),format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})

@CucumberOptions(features = ("features"),
glue = ("stepDefinitions"),
//format = {"pretty", "html:target/Destination"},
plugin = {"html:target/cucumber-reports/html",
		"json:target/cucumber-reports/cucumber.json",
		"junit:target/cucumber-reports/cucumber.xml",
		"pretty"},
tags = {"@Regression"}
)

public class WeatherApiRunnerTest {

}
