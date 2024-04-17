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
public class CreateUserTest {
    private final String email;
    private final String password;
    private final String name;
    private User user;
    private UserRequests userRequests;
    private String accessToken;

    // Конструктор для инициализации параметров теста
    public CreateUserTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Метод, предоставляющий данные для параметризованного теста
    @Parameterized.Parameters
    public static Object[][] getData() {
        Faker faker = new Faker();
        return new Object[][]{
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName()},
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName()},
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName()}
        };
    }

    // Выполняется перед каждым тестом
    @Before
    public void setUp() {
        RestAssured.requestSpecification = RequestSpec.requestSpecification(); //Устанавливаем базовую спецификацию
        user = new User(email, password, name); // Создаем объект пользователя
        userRequests = new UserRequests(); // Создаем объект для выполнения запросов к API
    }

    // Тест проверки создания пользователя
    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUser() {
        // Отправляем запрос на создание пользователя и получаем ответ
        Response responseCreate = userRequests.createUser(user);
        // Проверяем, что код состояния ответа равен 200 и значение "success" равно true
        responseCreate.then().statusCode(200).assertThat().body("success", equalTo(true));
        // Извлекаем токен доступа из ответа
        accessToken = responseCreate.then().log().all().extract().path("accessToken");
        System.out.println(accessToken); // Выводим токен доступа в консоль
    }

    // Выполняется после каждого теста
    @After
    public void deleteUser() {
        // Отправляем запрос на удаление пользователя с использованием полученного ранее токена доступа
        Response responseDelete = userRequests.deleteUser(accessToken);
        // Проверяем, что код состояния ответа равен 202 и значение "success" равно true
        responseDelete.then().log().all().statusCode(202).body("success", equalTo(true));
    }
}
