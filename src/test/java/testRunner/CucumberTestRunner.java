package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/resources/feature"},glue = {"stepDefinitionPackage"}
        ,monochrome = true,plugin = {"html:target/cucumber.html"})
public class CucumberTestRunner {
}
