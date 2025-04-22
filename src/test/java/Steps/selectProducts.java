package Steps;

import com.saucedemo.base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;
public class selectProducts extends Base {

    Float firstItemPrice;
    Float secondItemPrice;
    String firstItemDes;
    String secondItemDes;

    public selectProducts() throws IOException {
    }
    @Given("filter product from low to high")
    public void applyFilter(){
        WebElement priceLocator = driver.findElement(By.className("product_sort_container"));
        Select price = new Select(priceLocator);
        price.selectByValue("hilo");
    }
    @When("select first two products")
    public void selectItems(){
        WebElement first_item = driver.findElement(By.xpath("//div[@class='inventory_list']/div[1]/div[@class='inventory_item_description']/div[@class='pricebar']/button"));
        first_item.click();

        String first_item_price = driver.findElement(By.xpath("//div[@class='inventory_list']/div[1]/div[@class='inventory_item_description']/div[@class='pricebar']/div[@class='inventory_item_price']")).getText();
        first_item_price = first_item_price.substring(1);
        firstItemPrice = Float.parseFloat(first_item_price);
        firstItemDes = driver.findElement(By.xpath("//div[@class='inventory_list']/div[1]/div[@class='inventory_item_description']//div[@class='inventory_item_desc']")).getText();

        WebElement second_item = driver.findElement(By.xpath("//div[@class='inventory_list']/div[2]/div[@class='inventory_item_description']/div[@class='pricebar']/button"));
        second_item.click();

        secondItemDes = driver.findElement(By.xpath("//div[@class='inventory_list']/div[2]/div[@class='inventory_item_description']//div[@class='inventory_item_desc']")).getText();

        String second_item_price = driver.findElement(By.xpath("//div[@class='inventory_list']/div[2]/div[@class='inventory_item_description']/div[@class='pricebar']/div[@class='inventory_item_price']")).getText();
        second_item_price = second_item_price.substring(1);
        secondItemPrice = Float.parseFloat(second_item_price);
    }

    @Then("check products added to cart and open cart")
    public void checkCart(){
        By cart = By.className("shopping_cart_link");
        driver.findElement(cart).click();

        WebElement first = driver.findElement(By.xpath("//div[@class=\"cart_list\"]/div[3]//div[@class=\"inventory_item_desc\"]"));
        WebElement second = driver.findElement(By.xpath("//div[@class=\"cart_list\"]/div[4]//div[@class=\"inventory_item_desc\"]"));

        Assert.assertEquals(firstItemDes,first.getText());
        Assert.assertEquals(secondItemDes,second.getText());
    }
    @Then("go to checkout page")
    public void checkoutpage(){
        //checkout
        By checkout = By.id("checkout");
        driver.findElement(checkout).click();

        //check go to checkout page
        By continue_button = By.id("continue");
        Assert.assertTrue(driver.findElement(continue_button).isDisplayed());

        By first_name = By.id("first-name");
        driver.findElement(first_name).sendKeys("Menha");

        By last_name = By.id("last-name");
        driver.findElement(last_name).sendKeys("Mohamed");

        By zip_code = By.id("postal-code");
        driver.findElement(zip_code).sendKeys("123456");

        driver.findElement(continue_button).click();
    }

    @Then("go to finish page and check total price")
    public void finishPage(){

        By finish = By.id("finish");
        Assert.assertTrue(driver.findElement(finish).isDisplayed());

        By total = By.className("summary_subtotal_label");
        String total_price = driver.findElement(total).getText();
        total_price = total_price.substring(13);
        Float totalPrice = Float.parseFloat(total_price);
        Assert.assertEquals(totalPrice, firstItemPrice + secondItemPrice, 0.0000001 );
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");

        //finish
        driver.findElement(finish).click();
    }

    @Then("go to thanks page")
    public void thankspage(){

        By thanks = By.className("complete-header");
        Assert.assertTrue(driver.findElement(thanks).isDisplayed());

        By order_dispatched = By.className("complete-text");
        Assert.assertTrue(driver.findElement(order_dispatched).isDisplayed());
    }
}
