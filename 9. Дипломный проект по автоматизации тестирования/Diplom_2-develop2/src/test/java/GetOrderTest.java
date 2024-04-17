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
import methods.OrderRequests;
import methods.UserRequests;

import static constants.ApiConstants.BURGERS_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class GetOrderTest {
    private final String email; // Поле для хранения email пользователя
    private final String password; // Поле для хранения пароля пользователя
    private final String name; // Поле для хранения имени пользователя
    private UserRequests userRequests; // Объект для отправки запросов пользователей
    private OrderRequests orderRequests; // Объект для отправки запросов заказов
    private String accessToken; // Поле для хранения токена доступа

    // Конструктор для параметризованных тестов
    public GetOrderTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Метод для предоставления данных для параметризованных тестов
    @Parameterized.Parameters
    public static Object[][] getData() {
        Faker faker = new Faker();
        return new Object[][]{
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName()}, // Первый набор данных
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName()}, // Второй набор данных
                {faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName()} // Третий набор данных
        };
    }

    // Метод, выполняющийся перед каждым тестом
    @Before
    public void setUp()  {
        RestAssured.requestSpecification = RequestSpec.requestSpecification(); //Устанавливаем базовую спецификацию
        orderRequests = new OrderRequests(); // Инициализация объекта для отправки запросов заказов
        User user = new User(email, password, name); // Создание объекта пользователя
        userRequests = new UserRequests(); // Инициализация объекта для отправки запросов пользователей
        // Создание пользователя и получение токена авторизации
        Response responseCreate = userRequests.createUser(user);
        accessToken = responseCreate.then().log().all().extract().path("accessToken");
    }

    // Тест проверки получения заказов авторизованного пользователя
    @Test
    @DisplayName("Проверка получения заказов авторизованного пользователя")
    public void getOrderTest(){
        // Получение заказов авторизованного пользователя
        Response responseGetOrder = orderRequests.getOrderUser(accessToken);
        responseGetOrder.then().log().all().statusCode(200).body("success", equalTo(true)).body("orders.total", notNullValue());
    }

    // Тест проверки получения заказов неавторизованного пользователя
    @Test
    @DisplayName("Проверка получения заказов неавторизованного пользователя")
    public void getOrderWithoutAuthorization(){
        // Получение заказов неавторизованного пользователя
        Response responseGetOrderWithoutAuthorization = orderRequests.getOrderUserWithoutAuthorization();
        responseGetOrderWithoutAuthorization.then().log().all().statusCode(401).body("success", equalTo(false)).body("message", equalTo("You should be authorised"));
    }

    // Метод, выполняющийся после каждого теста
    @After
    public void deleteUser(){
        // Удаление пользователя
        Response responseDelete = userRequests.deleteUser(accessToken);
        responseDelete.then().log().all().statusCode(202).body("success", equalTo(true));
    }
}
