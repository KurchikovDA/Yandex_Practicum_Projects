import methods.RequestSpec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import methods.UserRequests;


import static constants.ApiConstants.BURGERS_URL;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CreateInvalidUserTest {
    // Поля для хранения данных пользователя и ожидаемых результатов
    private final String email;
    private final String password;
    private final String name;
    private final int statusCode;
    private final boolean success;
    private final String message;

    // Поля для хранения объектов пользователя и запросов пользователя
    private User user;
    private UserRequests userRequests;

    // Поля для хранения токена доступа и статуса ответа
    private String accessToken;

    // Конструктор для установки параметров
    public CreateInvalidUserTest(String email, String password, String name, int statusCode, boolean success, String message) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
    }

    // Метод для предоставления данных для тестов
    @Parameterized.Parameters
    public static Object[][] getData() {
        Faker faker = new Faker();
        return new Object[][]{
                // Создание пользователя без email
                {null, faker.internet().password(), faker.name().firstName(), 403, false, "Email, password and name are required fields"},
                // Создание пользователя без пароля
                {faker.internet().emailAddress(), null, faker.name().firstName(), 403, false, "Email, password and name are required fields"},
                // Создание пользователя без имени
                {faker.internet().emailAddress(), faker.internet().password(), null, 403, false, "Email, password and name are required fields"},
                // Создание дубликата пользователя
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName(), 200, true, null}
        };
    }

    // Метод для установки начальных условий перед тестом
    @Before
    public void setUp()  {
        RestAssured.requestSpecification = RequestSpec.requestSpecification(); //Устанавливаем базовую спецификацию
        user = new User(email, password, name);
        userRequests = new UserRequests();
    }

    // Тест для проверки создания пользователя с невалидными данными и попытки создания существующего пользователя
    @Test
    @DisplayName("Проверка создания пользователя с НЕвалидными данными и проверка возможности дублирования существующего пользователя")
    public void createInvalidUser() {
        Response responseCreate = userRequests.createUser(user);
        responseCreate.then().log().all().statusCode(statusCode).body("success", equalTo(success)).body("message", equalTo(message));

        // Если пользователь успешно создан, получаем токен доступа и пытаемся создать его снова
        if (responseCreate.statusCode() == 200) {
            accessToken = responseCreate.then().extract().path("accessToken");
            Response responseCreateDouble = userRequests.createUser(user);
            responseCreateDouble.then().log().all().statusCode(403).body("success", equalTo(false)).body("message", equalTo("User already exists"));
        }
    }

    // Метод для удаления пользователя после теста (если пользователь был создан успешно)
    @After
    public void deleteUser() {
        if (statusCode == 200) {
            userRequests.deleteUser(accessToken);
        }
    }
}
