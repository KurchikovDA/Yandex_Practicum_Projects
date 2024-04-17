import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.ForWhomScooterPage;

//Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
public class ScooterLogoTest {
    private WebDriver driver;
    private MainPage mainPage;
    private ForWhomScooterPage forWhomScooterPage;
    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/"; //Адрес главной страницы "Яндекс Самоката"

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // Хром
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        //driver = new FirefoxDriver(); // Мозилла
        mainPage = new MainPage(driver);
        forWhomScooterPage = new ForWhomScooterPage(driver);
        //Открываем сайт
        driver.get(SCOOTER_URL);
        //Клик на куки
        mainPage.clickCookieButton();
        //Клик на верхнюю кнопку "Заказать"
        mainPage.clickOrderUpButton();
    }
    @Test
    public void testScooterUrl() {
        mainPage.clickScooterLogo(); // Нажимаем на логотип Самоката

        // Проверяем, что текущий URL соответствует ожидаемому URL
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);

    }

    @After
    public void teardown() {
        driver.quit();
    }

}
