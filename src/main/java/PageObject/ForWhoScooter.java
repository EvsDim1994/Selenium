package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForWhoScooter {
    private final WebDriver driver;

    public ForWhoScooter(WebDriver driver){
        this.driver = driver;
    }


    private By inputName = By.xpath(".//input[@placeholder='* Имя']");
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By listOfMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    private By station = By.xpath(".//button[@value='4']");
    private By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//button[text()='Далее']");
    private By descriptionOrderWindow = By.className("Order_Header__BZXOb");


    public void checkOrderWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(descriptionOrderWindow));
    }

    public void inputName(String name){
        driver.findElement(inputName).sendKeys(name);
    }

    public void inputSurname(String surname){
        driver.findElement(inputSurname).sendKeys(surname);
    }

    public void inputAddress(String address){
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void openListMetro(){
        driver.findElement(listOfMetro).click();
    }

    public void clickMetroStation(){
        driver.findElement(station).click();
    }

    public void inputPhoneNumber(String phone){
        driver.findElement(phoneNumber).sendKeys(phone);
    }

    public RentPage clickNextButton(){
        driver.findElement(nextButton).click();
        return new RentPage(driver);
    }

    public void inputWhoScooterForm(String name, String surname, String address, String phone){
        //ожидание появления окна для оформления заказа
        checkOrderWindow();

        // ввод имени
        inputName(name);

        // ввод фамилии
        inputSurname(surname);

        // ввод  адреса
        inputAddress(address);

        // Открыть список станций
        openListMetro();

        // выбор станции метро
        clickMetroStation();

        // ввод номера телефона
        inputPhoneNumber(phone);
    }

}
