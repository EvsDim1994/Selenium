package PageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RentPage {
    private final WebDriver driver;

    public RentPage(WebDriver driver){
        this.driver = driver;
    }
    private By namePage = By.className("Order_Header__BZXOb");
    private By calendar = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By date = By.xpath(".//div[text()='21']");
    private By listRent = By.xpath(".//div[text()='* Срок аренды']");
    private By durationRent = By.xpath(".//div[text()='трое суток']");
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By submmit = By.xpath(".//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By buttonYes = By.xpath(".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By confirmOrderText = By.className("Order_ModalHeader__3FDaJ");


    public void checkRentWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(namePage));
    }

    public void openCalender(){
        driver.findElement(calendar).click();
    }

    public void chooseDate(){
        driver.findElement(date).click();
    }

    public void openListRentDuration(){
        driver.findElement(listRent).click();
    }

    public void chooseRentDuration(){
        driver.findElement(durationRent).click();
    }

    public void chooseColor(String color){
        driver.findElement(By.xpath(String.format(".//label[@for='%s']", color))).click();
    }

    public void inputComment(String commentText){
        driver.findElement(comment).sendKeys(commentText);
    }

    public void submitOrder(){
        driver.findElement(submmit).click();
    }

    public void inputRentForm(String commentText, String colorName){
        // Ожидание открытия окна Про аренду
        checkRentWindow();

        // Открыть календарь
        openCalender();

        // Выбор даты
        chooseDate();

        // Открыть список срока аренды
        openListRentDuration();

        // Выбрать срок аренды
        chooseRentDuration();

        // Выбор цвета
        chooseColor(colorName);

        // Ввод комментария
        inputComment(commentText);
    }

    public void clickYes(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']")));
        driver.findElement(buttonYes).click();
    }

    public void checkOrderConfirmation(){
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated
                        (confirmOrderText, "Заказ оформлен")));
    }
}
