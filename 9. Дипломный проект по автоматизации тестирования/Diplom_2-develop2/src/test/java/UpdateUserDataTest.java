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
public class UpdateUserDataTest {
    private Login login; // Поле для хранения данных для входа
    private final String oldEmail; // Поле для хранения старого email
    private final String newEmail; // Поле для хранения нового email
    private final String oldPassword; // Поле для хранения старого пароля
    private final String newPassword; // Поле для хранения нового пароля
    private final String oldName; // Поле для хранения старого имени
    private final String newName; // Поле для хранения нового имени
    private UserRequests userRequests; // Поле для выполнения запросов к API
    private String accessToken; // Поле для хранения токена доступа


    // Конструктор для параметризованных тестов
    public UpdateUserDataTest(String oldEmail, String newEmail, String oldPassword, String newPassword, String oldName, String newName) {
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.oldName = oldName;
        this.newName = newName;
    }

    // Метод для предоставления данных для параметризованных тестов
    @Parameterized.Parameters
    public static Object[][] getData() {
        Faker faker = new Faker();
        return new Object[][]{
                // Обновляются все данные
                {"cobain@yandex.com", faker.internet().emailAddress(), "013969", faker.internet().password(), "David", faker.name().firstName()},
                // Обновляется только email
                {"cobain@yandex.com", faker.internet().emailAddress(), "013969", "013969", "David", "David"},
                // Обновляется только пароль
                {"cobain@yandex.com", "cobain@yandex.com", "013969", faker.internet().password(), "David", "David"},
                // Обновляется только имя
                {"cobain@yandex.com", "novoselic@yandex.com", "013969", "013969", "David", faker.name().firstName()}
        };
    }

    // Метод, выполняющийся перед каждым тестом
    @Before
    public void setUp() {
        RestAssured.baseURI = BURGERS_URL; // Установка базового URI для запросов
        User user = new User(oldEmail, oldPassword, oldName);
        userRequests = new UserRequests(); // Создание объекта для выполнения запросов к API
        login = new Login(newEmail, newPassword);
        // Создание пользователя и получение токена
        Response responseCreate = userRequests.createUser(user);
        accessToken = responseCreate.then().log().all().extract().path("accessToken");
    }

    // Тест обновления данных авторизованного пользователя
    @Test
    @DisplayName("Проверка изменения данных авторизованного пользователя")
    public void modifyUserDataTest() {
        // Получение данных пользователя (email, имя)
        Response responseGetUser = userRequests.getUser(accessToken);
        responseGetUser.then().log().all().statusCode(200).body("success", equalTo(true)).body("user.email", equalTo(oldEmail)).body("user.name", equalTo(oldName));
        // Обновление данных пользователя
        Response responseUpdate = userRequests.updateUser(new User(newEmail, newPassword, newName), accessToken);
        responseUpdate.then().log().all().statusCode(200).body("success", equalTo(true)).body("user.email", equalTo(newEmail)).body("user.name", equalTo(newName));
        // Получение обновленных данных пользователя
        Response responseGetUserAfterUpdate = userRequests.getUser(accessToken);
        responseGetUserAfterUpdate.then().log().all().statusCode(200).body("success", equalTo(true)).body("user.email", equalTo(newEmail)).body("user.name", equalTo(newName));
        // Логин пользователя (для проверки обновленного пароля)
        Response responseLogin = userRequests.loginUser(login);
        responseLogin.then().log().all().statusCode(200);
    }

    // Тест обновления данных неавторизованного пользователя
    @Test
    @DisplayName("Проверка изменения данных неавторизованного пользователя")
    public void modifyUserDataWithoutAuthorizationTest() {
        // Обновление данных неавторизованного пользователя
        Response responseUpdateWithoutAuthorization = userRequests.updateUserWithoutAuthorization(new User(newEmail, newPassword, newName));
        responseUpdateWithoutAuthorization.then().log().all().statusCode(401).body("success", equalTo(false)).body("message", equalTo("You should be authorised"));
    }

    // Метод, выполняющийся после каждого теста
    @After
    public void deleteUser() {
        Response responseDelete = userRequests.deleteUser(accessToken);
        responseDelete.then().log().all().statusCode(202).body("success", equalTo(true));
    }
}
