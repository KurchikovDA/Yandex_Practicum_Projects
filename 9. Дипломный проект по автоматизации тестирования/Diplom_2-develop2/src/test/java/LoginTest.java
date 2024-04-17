import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Login;
import models.User;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import methods.UserRequests;

import static constants.ApiConstants.BURGERS_URL;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginTest {
    private final String email; // Поле для хранения email пользователя
    private final String password; // Поле для хранения пароля пользователя
    private final int statusCode; // Поле для хранения ожидаемого HTTP-кода ответа
    private final boolean success; // Поле для хранения ожидаемого значения поля success в ответе
    private User user; // Объект пользователя
    private UserRequests userRequests; // Объект для отправки запросов
    private Login login; // Объект для аутентификации
    private String accessToken; // Поле для хранения токена доступа
    private final int createStatusCode; // Поле для хранения ожидаемого HTTP-кода создания пользователя
    private final String message; // Поле для хранения ожидаемого сообщения об ошибке

    // Конструктор для параметризованных тестов
    public LoginTest(String email, String password, int statusCode, boolean success, int createStatusCode, String message) {
        this.email = email;
        this.password = password;
        this.statusCode = statusCode;
        this.success = success;
        this.createStatusCode = createStatusCode;
        this.message = message;
    }

    // Метод для предоставления данных для параметризованных тестов
    @Parameterized.Parameters
    public static Object[][] getData() {
        Faker faker = new Faker();
        return new Object[][]{
                {"cobain@yandex.com", "!nirvana969", 200, true, 200, null}, // Успешная авторизация
                {"cobain@yandex.com", faker.internet().password(), 401, false, 200, "email or password are incorrect"}, // Некорректный пароль
                {null, "!nirvana969", 401, false, 200, "email or password are incorrect"}, // Отсутствие email
                {"cobain@yandex.com", null, 401, false, 200, "email or password are incorrect"}, // Отсутствие пароля
                {faker.internet().emailAddress(), "987283467", 401, false, 200, "email or password are incorrect"} // Несуществующий email
        };
    }

    // Метод, выполняющийся перед каждым тестом
    @Before
    public void setUp()  {
        RestAssured.baseURI = BURGERS_URL; // Установка базового URI для запросов
        user = new User("cobain@yandex.com", "!nirvana969", "Kurt"); // Создание тестового пользователя
        userRequests = new UserRequests(); // Инициализация объекта для отправки запросов
        login = new Login(email, password); // Создание объекта для аутентификации
    }

    // Тест проверки аутентификации пользователя
    @Test
    @DisplayName("Проверка логина пользователя")
    public void loginUser(){
        Response responseCreate = userRequests.createUser(user); // Отправка запроса на создание пользователя
        accessToken = responseCreate.then().log().all().statusCode(createStatusCode).extract().path("accessToken"); // Извлечение токена доступа из ответа
        Response responseLogin = userRequests.loginUser(login); // Отправка запроса на аутентификацию
        responseLogin.then().log().all().statusCode(statusCode).body("success", equalTo(success)).body("message", equalTo(message)); // Проверка ответа
    }

    // Метод, выполняющийся после каждого теста
    @After
    public void deleteUser() {
        if (createStatusCode == 200) { // Если код создания пользователя равен 200
            Response responseDelete = userRequests.deleteUser(accessToken); // Отправка запроса на удаление пользователя
            responseDelete.then().log().all().statusCode(202).body("success", equalTo(true)); // Проверка ответа
        }
    }
}
