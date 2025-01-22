import PageObject.EnvConfig;
import PageObject.ForWhoScooter;
import PageObject.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@RunWith(Parameterized.class)
public class MakeOrderTests {
    private final By celector;
    private final String colorName;
    private final String textComment;

    public MakeOrderTests(By celector, String colorName, String textComment) {
        this.celector = celector;
        this.colorName = colorName;
        this.textComment = textComment;
    }

    @Rule
    public DriverRule factory = new DriverRule();

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = factory.getDriver();
        driver.get(EnvConfig.BASE_URL);
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {By.xpath(".//button[@class='Button_Button__ra12g']"), "black", "Оставить у подъезда"},
                {By.className("Button_Middle__1CSJM"), "grey", ""}
        };
    }


    @Test()
    public void makeOrderButton(){
        MainPage mainPage = new MainPage(driver);

        // нажать на кнопку cookie
        mainPage.clickOnCookie();

        // нажатие на кнопку заказать в верхней части страницы
        mainPage.clickOrderButton(celector);

        ForWhoScooter forWhoScooter = new ForWhoScooter(driver);

        forWhoScooter.inputWhoScooterForm(
                "Дмитрий",
                "Евсюков",
                "Москва, Сокольники 11",
                "89884900639");

        // нажатие кнопки далее
        var rentPage = forWhoScooter.clickNextButton();

        rentPage.inputRentForm(textComment, colorName);

        // Нажатие кнопки Заказать
        rentPage.submitOrder();

        // Подтверждение заказа
        rentPage.clickYes();

        // Ожидание окна с тектом оформления заказа
        rentPage.checkOrderConfirmation();

    }
}
