import PageObject.EnvConfig;
import PageObject.ForWhoScooter;
import PageObject.MainPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
@RunWith(Parameterized.class)
public class MakeOrderTests {
    private final String button;
    private final String colorName;
    private final String textComment;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;


    public MakeOrderTests(String button,
                          String colorName,
                          String textComment,
                          String name,
                          String surname,
                          String address,
                          String phone) {
        this.button = button;
        this.colorName = colorName;
        this.textComment = textComment;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
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
                {"buttonTop","black", "Оставить у подъезда", "Анатолий", "Грецин", "Москва, Лужники 12", "89777777777"},
                {"buttonDown","grey", "", "Дмитрий", "Евсюков", "Москва, Сокольники 11", "89884900639"}
        };
    }


    @Test()
    public void makeOrderButton(){
        MainPage mainPage = new MainPage(driver);

        // нажать на кнопку cookie
        mainPage.clickOnCookie();

        // нажатие на кнопку заказать в верхней части страницы
        mainPage.clickOrderButton(button);

        ForWhoScooter forWhoScooter = new ForWhoScooter(driver);

        forWhoScooter.inputWhoScooterForm(
                name,
                surname,
                address,
                phone);

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
