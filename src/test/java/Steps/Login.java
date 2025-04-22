package Steps;

import com.saucedemo.base.Base;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Login extends Base {
    By usernameField;
    By passwordField;
    By loginB;

    public Login() throws IOException {
    }

    @After
    public static void exit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Given("open the website and go to login page")
    public void open_the_website_and_go_to_login_page() {
        start();
        usernameField = By.id("user-name");
        passwordField = By.id("password");
        loginB = By.id("login-button");
    }

    @When("enter {string} and {string}")
    public void enter_username_and_password(String username,String password){
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginB).click();
    }

    @Then("a message displayed")
    public void a_message_displayed(){
        By errorMessage = By.xpath("//h3");
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
    }

    @Then("go to home page")
    public void homepage(){

        WebElement appLogo = driver.findElement(By.className("app_logo"));
        Assert.assertTrue(appLogo.isDisplayed());

    }
}
