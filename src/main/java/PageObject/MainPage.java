package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }


    private By cookie = By.className("App_CookieButton__3cvqF");
    private By buttonScooter = By.xpath(".//img[@alt='Scooter']");
    private By mainDescription = By.className("Home_Header__iJKdX");
    private By buttonYandex = By.xpath(".//img[@alt='Yandex']");
    private By status = By.className("Header_Link__1TAG7");
    private By inputOrderNumber = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')]");
    private By buttonGo = By.cssSelector(".Header_Button__28dPO");
    private By notFound = By.xpath(".//img[@alt='Not found']");
    private By buttonTop = By.xpath(".//button[@class='Button_Button__ra12g']");
    private By buttonDown = By.className("Button_Middle__1CSJM");


    public void clickOnCookie(){
        driver.findElement(cookie).click();
    }

    public void assetTextQuestion(String text, String questionNumber){
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        By.id(String.format("accordion__panel-%s", questionNumber)),
                        text)));
        driver.findElement(By.id(String.format("accordion__panel-%s", questionNumber))).getText();
    }

    public void clickQuestion(String questionNumber){
        driver.findElement(By.xpath(String.format(".//div[@id='accordion__heading-%s']", questionNumber))).click();
    }

    public void clickScooter(){
        driver.findElement(buttonScooter).click();
    }

    public void checkMainDescription(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(mainDescription));

        assertTrue(driver.findElement(mainDescription).isDisplayed());
    }

    public void clickYandex(){
        driver.findElement(buttonYandex).click();
    }

    public void clickStatus(){
        driver.findElement(status).click();
    }

    public void inputOrderNumber(String number){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(inputOrderNumber));
        driver.findElement(inputOrderNumber).sendKeys(number);
    }

    public void clickGo(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(buttonGo));
        driver.findElement(buttonGo).click();
    }

    public void checkOrderError(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(notFound));

        assertTrue(driver.findElement(notFound).isDisplayed());
    }

    public void checkChangeWindow(String url){
        String mainWindow = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.urlToBe("https://dzen.ru/?yredirect=true"));

        assertEquals(url, driver.getCurrentUrl());
    }

    public void clickOrderButton(String button){
        if (button.equalsIgnoreCase("buttonTop"))
            driver.findElement(buttonTop).click();
        else if(button.equalsIgnoreCase("buttonDown")){
            driver.findElement(buttonDown).click();
        }
    }
}
