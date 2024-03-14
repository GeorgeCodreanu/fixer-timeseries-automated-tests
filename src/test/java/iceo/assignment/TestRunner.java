package iceo.assignment;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.serenitybdd.annotations.Feature;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features"
)public class TestRunner {
}