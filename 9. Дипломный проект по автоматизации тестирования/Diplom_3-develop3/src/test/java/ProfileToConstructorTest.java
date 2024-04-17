import client.ClientFaker;
import client.ClientSteps;
import io.qameta.allure.junit4.DisplayName; // Импортируем аннотацию DisplayName из библиотеки Allure
import io.restassured.RestAssured; // Импортируем класс RestAssured для работы с HTTP-запросами
import org.junit.*; // Импортируем JUnit аннотации и классы
import org.openqa.selenium.WebDriver; // Импортируем интерфейс WebDriver для работы с браузером
import org.openqa.selenium.chrome.ChromeDriver; // Импортируем класс ChromeDriver для управления браузером Chrome
import pages.LoginPage; // Импортируем класс LoginPage для работы с страницей входа
import pages.MainPage; // Импортируем класс MainPage для работы с главной страницей
import pages.ProfilePage; // Импортируем класс ProfilePage для работы с профилем пользователя
import client.Client; // Импортируем класс Client для работы с данными пользователя

import java.util.concurrent.TimeUnit;

public class ProfileToConstructorTest extends BrowserConfiguration {
    private Client client; // Объявляем переменную client для хранения данных пользователя
    private String accessToken; // Объявляем переменную accessToken для хранения токена доступа

    // Метод запускается перед каждым тестом
    @Before
    public void setUp() {
        // Открываем базовый URL
        driver.get(ClientSteps.baseURL);
        // Устанавливаем базовый URL для REST-запросов
        RestAssured.baseURI = ClientSteps.baseURL;
        // Генерируем случайного пользователя
        client = ClientFaker.getRandomClient();
        // Регистрируем нового пользователя и извлекаем токен доступа
        accessToken = ClientSteps.createNewClient(client).then().extract().path("accessToken");
        // Максимизируем окно браузера
        driver.manage().window().maximize();
        // Устанавливаем неявное ожидание на 10 секунд
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Тест на переход из личного кабинета в конструктор по клику на «Конструктор»
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void profileToConstructorFromButtonTest() {
        MainPage mainPage = new MainPage(driver); // Создаем экземпляр MainPage
        LoginPage loginPage = new LoginPage(driver); // Создаем экземпляр LoginPage
        ProfilePage profilePage = new ProfilePage(driver); // Создаем экземпляр ProfilePage
        mainPage.clickAccountButton(); // Нажимаем кнопку "Личный кабинет" на главной странице
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Заполняем данные пользователя на странице входа
        loginPage.clickLoginButton(); // Нажимаем кнопку "Войти" на странице входа
        mainPage.clickAccountButton(); // Снова нажимаем кнопку "Личный кабинет"
        profilePage.clickConstructorButton(); // Нажимаем кнопку "Конструктор" на странице профиля
        String text = mainPage.getCreateOrderButtonText(); // Получаем текст кнопки "Оформить заказ"
        Assert.assertEquals("Оформить заказ", text); // Проверяем, что текст кнопки соответствует ожидаемому
    }

    // Тест на переход из личного кабинета в конструктор по клику на логотип Stellar Burgers
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void profileToConstructorFromLogoTest() {
        MainPage mainPage = new MainPage(driver); // Создаем экземпляр MainPage
        LoginPage loginPage = new LoginPage(driver); // Создаем экземпляр LoginPage
        ProfilePage profilePage = new ProfilePage(driver); // Создаем экземпляр ProfilePage
        mainPage.clickAccountButton(); // Нажимаем кнопку "Личный кабинет" на главной странице
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Заполняем данные пользователя на странице входа
        loginPage.clickLoginButton(); // Нажимаем кнопку "Войти" на странице входа
        mainPage.clickAccountButton(); // Снова нажимаем кнопку "Личный кабинет"
        profilePage.clickLogoButton(); // Нажимаем на логотип Stellar Burgers на странице профиля
        String text = mainPage.getCreateOrderButtonText(); // Получаем текст кнопки "Оформить заказ"
        Assert.assertEquals("Оформить заказ", text); // Проверяем, что текст кнопки соответствует ожидаемому
    }

    // Метод, выполняемый после каждого теста
    @After
    public void tearDown() {
        // Закрываем драйвер
        driver.quit();
        // Если токен доступа был получен, удаляем пользователя
        if (accessToken != null) {
            ClientSteps.deleteClient(accessToken);
        }
    }
}
