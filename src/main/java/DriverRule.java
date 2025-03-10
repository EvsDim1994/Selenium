import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public void initDriver() {
        if ("firefox".equalsIgnoreCase(System.getProperty("browser"))) {
            startUpFireFox();
        } else if("chrome".equalsIgnoreCase(System.getProperty("browser"))) {
            startUpChrome();
        } else {
            startUpChrome();
        }
    }
    public WebDriver getDriver(){
        return driver;
    }

    public void startUpChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void startUpFireFox(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
}
