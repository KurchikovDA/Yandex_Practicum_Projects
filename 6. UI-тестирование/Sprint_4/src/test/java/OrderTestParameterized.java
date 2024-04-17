import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AboutRentPage;
import pages.MainPage;
import pages.ForWhomScooterPage;

//Заказ самоката. Весь флоу позитивного сценария. Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
//Из чего состоит позитивный сценарий:
//Нажать кнопку «Заказать». На странице две кнопки заказа.
//Заполнить форму заказа.
//Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
@RunWith(Parameterized.class)
public class OrderTestParameterized {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String comment;

    private WebDriver driver;
    private MainPage mainPage;
    private ForWhomScooterPage forWhomScooterPage;
    private AboutRentPage aboutRentPage;

    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/"; //Адрес главной страницы "Яндекс Самоката"

    //Конструктор
    public OrderTestParameterized(String firstName, String lastName, String address, String phoneNumber, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Курт", "Кобейн", "Сиэтл", "79998887766", "28.12.2023", "With the lights out!"},
                {"Крист", "Новоселич", "Вашингтон", "79994445566", "31.12.2026", "Привет, меня зовут Крист!"},
                {"Дейв", "Грол", "Лос-Анджелес", "79998833766", "01.07.2024", "FooFighters"},
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // Хром
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        //driver = new FirefoxDriver(); // Мозилла
        mainPage = new MainPage(driver);
        forWhomScooterPage = new ForWhomScooterPage(driver);
        aboutRentPage = new AboutRentPage(driver);
        driver.get(SCOOTER_URL);
        mainPage.clickCookieButton();
        driver.manage().window().maximize();  //Расширение экрана

    }
    @Test

    public void testFullOrderByFirstButton() {
        mainPage.clickOrderUpButton(); //Клик на верхнюю кнопку "Заказать" на главной странице

        forWhomScooterPage.fillOrderForm1(firstName, lastName, address, phoneNumber);  //Заполняется форма "Для кого самокат"
        forWhomScooterPage.nextButtonClick(); //Клик на кнопку "Далее"

        aboutRentPage.fillOrderForm2(deliveryDate, comment); // Заполняется форма "Про аренду"

        aboutRentPage.clickPlaceOrderButton(); // Клик на кнопку "Заказать"
        aboutRentPage.clickYesButton(); //Клик на кнопку "Да"
        //aboutRentPage.clickCheckStatusButton(); // Клик на кнопку "Оформить заказ", но пока её не трогаем.
        //aboutRentPage.assertCheckStatusButtonVisible(); //Проверка, что кнопка "Оформить заказ" Появилась, значит тест пройден, запасной вариант
        aboutRentPage.assertOrderDoneTextVisible(); //Проверка, что заголовок "Заказ оформлен" видим
    }

    /*@Test    //Тест второй кнопки, убрал в отдельный класс SecondOrderButtonTest, чтобы не повторялся.
    public void testSecondOrderButton() {
        mainPage.scrollToOrderDownButtonAndClick(); //Скролл и клик на нижнюю кнопку "Заказать" на главной странице
        forWhomScooterPage.assertOrderDoneTextVisible(); //Проверяется наличие текста "Для кого самокат"
    } */

    @After
    public void tearDown() {
        driver.quit();
    }
}
