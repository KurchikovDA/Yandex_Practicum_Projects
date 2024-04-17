import methods.RequestSpec;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Order;

import org.junit.runners.Parameterized;
import methods.OrderRequests;

import java.util.ArrayList;
import java.util.List;

import static constants.ApiConstants.BURGERS_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final int fromIndex; // Начальный индекс для выбора ингредиентов
    private final int toIndex; // Конечный индекс для выбора ингредиентов
    private final int statusCode; // Ожидаемый статус-код ответа
    private OrderRequests orderRequests; // Объект для отправки запросов заказов

    // Конструктор для параметризованных тестов
    public CreateOrderTest(int fromIndex, int toIndex, int statusCode) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.statusCode = statusCode;
    }

    // Метод для предоставления данных для параметризованных тестов
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                // Добавить один ингредиент
                {0, 1, 200},
                // Добавить несколько ингредиентов
                {0, 6, 200},
                // Добавить несколько ингредиентов
                {10, 15, 200},
                // Не добавлять ингредиенты
                {0, 0, 400},
        };
    }

    // Метод, выполняющийся перед каждым тестом
    @Before
    public void setUp() {
        RestAssured.requestSpecification = RequestSpec.requestSpecification(); //Устанавливаем базовую спецификацию
        orderRequests = new OrderRequests(); // Инициализация объекта для отправки запросов заказов
    }

    // Тест создания заказа
    @Test
    @DisplayName("Проверка создания заказа")
    public void createOrder() {
        Response responseGetIngredient = orderRequests.getIngredient(); // Получение списка ингредиентов
        List<String> ingredients = new ArrayList<>(responseGetIngredient.then().log().all().statusCode(200).extract().path("data._id")); // Извлечение ID ингредиентов из ответа
        Order order = new Order(ingredients.subList(fromIndex, toIndex)); // Создание заказа на основе выбранных ингредиентов
        Response responseCreate = orderRequests.createOrder(order); // Отправка запроса на создание заказа
        if (statusCode == 200) {
            responseCreate.then().log().all().assertThat().body("order.number", notNullValue()).body("success", equalTo(true)); // Проверка успешного создания заказа
        } else if (statusCode == 400) {
            responseCreate.then().log().all().assertThat().body("success", equalTo(false)).body("message", equalTo("Ingredient ids must be provided")); // Проверка неуспешного создания заказа с неверными данными
        }
    }
}
