import client.ClientSteps;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import client.Client;
import client.ClientFaker;

import java.util.concurrent.TimeUnit;

public class TransitionToProfileTest extends BrowserConfiguration {
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

    // Тест на переход в личный кабинет
    @Test
    @DisplayName("Переход в личный кабинет")
    public void transitionToProfileTest() {
        MainPage mainPage = new MainPage(driver); // Создаем экземпляр MainPage
        LoginPage loginPage = new LoginPage(driver); // Создаем экземпляр LoginPage
        ProfilePage profilePage = new ProfilePage(driver); // Создаем экземпляр ProfilePage
        mainPage.clickAccountButton(); // Нажимаем кнопку "Личный кабинет"
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Вводим данные пользователя
        loginPage.clickLoginButton(); // Нажимаем кнопку "Войти"
        mainPage.clickAccountButton(); // Нажимаем кнопку "Личный кабинет"
        String text = profilePage.getLogoutButtonText(); // Получаем текст на кнопке выхода
        Assert.assertEquals("Выход", text); // Проверяем, что текст соответствует ожидаемому
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
