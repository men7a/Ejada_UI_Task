package Steps;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features", // مسار ملف .feature
        glue = "Steps", // الباكدج اللي فيه الخطوات
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class TestRunner {}
