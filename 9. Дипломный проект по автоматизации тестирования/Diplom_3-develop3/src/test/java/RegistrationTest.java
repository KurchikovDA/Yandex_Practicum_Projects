import client.Client;
import client.ClientLogin;
import client.ClientSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pages.MainPage;
import pages.LoginPage;
import pages.RegisterPage;
import client.ClientFaker;
import io.restassured.RestAssured;

import java.util.concurrent.TimeUnit;

public class RegistrationTest extends BrowserConfiguration {
    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        // Открываем веб-сайт
        driver.get(ClientSteps.baseURL);
        // Максимизируем окно браузера
        driver.manage().window().maximize();
        // Устанавливаем неявное ожидание на 10 секунд
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registerNewClientTest() {
        // Генерируем случайного пользователя
        client = ClientFaker.getRandomClient();
        // Создаем экземпляры страниц
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        // Переходим на страницу входа
        mainPage.clickLoginButton();
        // Нажимаем кнопку "Зарегистрироваться"
        loginPage.clickRegisterButton();
        // Заполняем информацию о пользователе
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        // Нажимаем кнопку "Зарегистрироваться"
        registerPage.clickRegisterButton();
        // Получаем текст заголовка страницы входа
        String text = loginPage.getEnterLabelText();
        // Проверяем, что текст заголовка соответствует ожидаемому тексту "Вход"
        Assert.assertEquals("Вход", text);
        // Логинимся с зарегистрированным пользователем и получаем токен доступа
        ClientLogin login = new ClientLogin(client.getEmail(), client.getPassword());
        RestAssured.baseURI = ClientSteps.baseURL;
        accessToken = ClientSteps.loginClient(login).then().extract().path("accessToken");
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registerNewClientWithWrongPasswordTest() {
        // Генерируем случайного пользователя с некорректным паролем
        client = ClientFaker.getRandomClientWithWrongPassword();
        // Создаем экземпляры страниц
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        // Переходим на страницу входа
        mainPage.clickLoginButton();
        // Нажимаем кнопку "Регистрация"
        loginPage.clickRegisterButton();
        // Заполняем информацию о пользователе
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        // Нажимаем кнопку "Зарегистрироваться"
        registerPage.clickRegisterButton();
        // Проверяем, что отобразилось сообщение о некорректном пароле
        String text = registerPage.getPasswordErrorText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    @After
    public void tearDown() {
        // Закрываем браузер после выполнения теста
        driver.quit();
        // Если токен доступа был получен, пытаемся удалить пользователя
        if (accessToken != null) {
            try {
                ClientSteps.deleteClient(accessToken);
            } catch (Exception e) {
                // Если возникла ошибка при удалении пользователя, выводим сообщение об ошибке
                System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
            }
        }
    }
}
