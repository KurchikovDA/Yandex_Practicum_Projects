import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.ForWhomScooterPage;

public class SecondOrderButtonTest {
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
        driver.get(SCOOTER_URL);
        mainPage.clickCookieButton();
        driver.manage().window().maximize();  //Расширение экрана
    }

    @Test
    public void testSecondOrderButton() {
        mainPage.scrollToOrderDownButtonAndClick(); //Скролл и клик на нижнюю кнопку "Заказать" на главной странице
        forWhomScooterPage.assertOrderDoneTextVisible(); //Проверяется наличие текста "Для кого самокат"
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
