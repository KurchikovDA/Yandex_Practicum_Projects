import client.Client;
import client.ClientSteps;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import client.ClientFaker;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BrowserConfiguration  {
    private Client client; // Поле для хранения данных о пользователе
    private String accessToken; // Поле для хранения токена доступа

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

    // Тест на вход с главной страницы через кнопку "Войти в аккаунт"
    @Test // Обозначение метода как теста
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной") // Установка отображаемого имени теста для отчета Allure
    public void loginFromMainPageTest(){ // Определение метода теста
        MainPage mainPage = new MainPage(driver); // Создание экземпляра класса MainPage
        LoginPage loginPage = new LoginPage(driver); // Создание экземпляра класса LoginPage
        mainPage.clickLoginButton(); // Вызов метода clickLoginButton() для нажатия на кнопку "Войти в аккаунт"
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Установка данных пользователя
        loginPage.clickLoginButton(); // Выполнение входа, нажимая на кнопку "Войти"
        String text = mainPage.getCreateOrderButtonText(); // Получение текста кнопки для проверки
        Assert.assertEquals("Оформить заказ", text); // Проверка, что текст кнопки соответствует ожидаемому
    }

    // Тест на вход через кнопку "Личный кабинет"
    @Test // Обозначение метода как теста
    @DisplayName("Вход через кнопку «Личный кабинет»") // Установка отображаемого имени теста для отчета Allure
    public void loginFromProfilePageTest(){ // Определение метода теста
        MainPage mainPage = new MainPage(driver); // Создание экземпляра класса MainPage
        LoginPage loginPage = new LoginPage(driver); // Создание экземпляра класса LoginPage
        mainPage.clickAccountButton(); // Нажатие на кнопку "Личный кабинет"
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Установка данных пользователя
        loginPage.clickLoginButton(); // Выполнение входа, нажимая на кнопку "Войти"
        String text = mainPage.getCreateOrderButtonText(); // Получение текста кнопки для проверки
        Assert.assertEquals("Оформить заказ", text); // Проверка, что текст кнопки соответствует ожидаемому
    }

    // Тест на вход через кнопку в форме регистрации
    @Test // Обозначение метода как теста
    @DisplayName("Вход через кнопку в форме регистрации") // Установка отображаемого имени теста для отчета Allure
    public void loginFromRegisterPageTest(){ // Определение метода теста
        MainPage mainPage = new MainPage(driver); // Создание экземпляра класса MainPage
        LoginPage loginPage = new LoginPage(driver); // Создание экземпляра класса LoginPage
        RegisterPage registerPage = new RegisterPage(driver); // Создание экземпляра класса RegisterPage
        mainPage.clickAccountButton(); // Нажатие на кнопку "Личный кабинет"
        loginPage.clickRegisterButton(); // Нажатие на кнопку "Регистрация"
        registerPage.clickLoginButton(); // Нажатие на кнопку "Войти"
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Установка данных пользователя
        loginPage.clickLoginButton(); // Выполнение входа, нажимая на кнопку "Войти"
        String text = mainPage.getCreateOrderButtonText(); // Получение текста кнопки для проверки
        Assert.assertEquals("Оформить заказ", text); // Проверка, что текст кнопки соответствует ожидаемому
    }

    // Тест на вход через кнопку в форме восстановления пароля
    @Test // Обозначение метода как теста
    @DisplayName("Вход через кнопку в форме восстановления пароля") // Установка отображаемого имени теста для отчета Allure
    public void loginFromForgotPasswordPageTest(){ // Определение метода теста
        MainPage mainPage = new MainPage(driver); // Создание экземпляра класса MainPage
        LoginPage loginPage = new LoginPage(driver); // Создание экземпляра класса LoginPage
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver); // Создание экземпляра класса ForgotPasswordPage
        mainPage.clickAccountButton(); // Нажатие на кнопку "Личный кабинет"
        loginPage.clickForgotPasswordButton(); // Нажатие на кнопку "Восстановить пароль"
        forgotPasswordPage.clickLoginButton(); // Нажатие на кнопку "Войти"
        loginPage.setClientLoginData(client.getEmail(), client.getPassword()); // Установка данных пользователя
        loginPage.clickLoginButton(); // Выполнение входа, нажимая на кнопку "Войти"
        String text = mainPage.getCreateOrderButtonText(); // Получение текста кнопки для проверки
        Assert.assertEquals("Оформить заказ", text); // Проверка, что текст кнопки соответствует ожидаемому
    }

    // Метод, выполняемый после каждого теста
    @After
    public void tearDown(){
        // Закрываем драйвер
        driver.quit();
        // Если токен доступа был получен, удаляем пользователя
        if (accessToken != null) {
            ClientSteps.deleteClient(accessToken);
        }
    }
}
