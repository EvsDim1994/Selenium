import PageObject.EnvConfig;
import PageObject.ForWhoScooter;
import PageObject.MainPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AdditionalTests {
    @Rule
    public DriverRule factory = new DriverRule();

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URL);
    }


    @Test
    public void mainPageLogo(){
        MainPage mainPage = new MainPage(driver);

        // нажать на кнопку cookie
        mainPage.clickOnCookie();

        // нажатие на кнопку заказать в верхней части страницы
        mainPage.clickOrderButton("buttonTop");

        //ожидание появления окна для оформления заказа
        ForWhoScooter forWhoScooter = new ForWhoScooter(driver);
        forWhoScooter.checkOrderWindow();

        // нажатие на кнопку Самокат
        mainPage.clickScooter();

        //ожидание появления главной страницы
        mainPage.checkMainDescription();
    }

    @Test
    public void checkSwitchToDzen(){
        MainPage mainPage = new MainPage(driver);

        // нажать на кнопку cookie
        mainPage.clickOnCookie();

        // нажатие на кнопку Яндекс
        mainPage.clickYandex();

        // проверка перехода на страницу Дзен
        mainPage.checkChangeWindow("https://dzen.ru/?yredirect=true");
    }

    @Test
    public void checkErrorForOrder(){
        MainPage mainPage = new MainPage(driver);

        // нажать на кнопку cookie
        mainPage.clickOnCookie();

        // нажатие на кнопку Статус
        mainPage.clickStatus();


        // ввод номера заказа
        mainPage.inputOrderNumber("12345");

        // ожидание доступности кнопки Go
        mainPage.clickGo();

        //ожидание появления окна для неккоректного заказа
        mainPage.checkOrderError();
    }
}
