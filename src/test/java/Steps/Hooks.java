package Steps;

import com.saucedemo.base.Base;
import org.junit.After;

import java.io.IOException;

public class Hooks extends Base {
    public Hooks() throws IOException {
    }

    @After
    public void afterAnyScenario(){
        driver.quit();
    }
}
