import methods.RequestSpec;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Order;

import org.junit.runners.Parameterized;
import methods.OrderRequests;

import java.util.List;

@RunWith(Parameterized.class)
public class InvalidOrderHashCreationTest {
    private final List<String> ingredients; // Список ингредиентов для создания заказа
    private OrderRequests orderRequests; // Объект для отправки запросов заказов
    private Order order; // Объект заказа

    // Конструктор класса, принимающий список ингредиентов для создания заказа
    public InvalidOrderHashCreationTest(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    // Параметризованный метод для генерации тестовых данных
    @Parameterized.Parameters
    public static Object[][] getData(){
        Faker faker = new Faker();
        return new Object[][]{
                {List.of(faker.number().digits(7))}, // Не валидный хэш (7 символов)
                {List.of(faker.number().digits(23))}, // Не валидный хэш (23 символа)
                {List.of(faker.number().digits(25))} // Не валидный хэш (25 символов)
        };
    }

    @Before
    public void setUp()  {
        RestAssured.requestSpecification = RequestSpec.requestSpecification(); //Устанавливаем базовую спецификацию
        orderRequests = new OrderRequests(); // Инициализация объекта для отправки запросов заказов
        order = new Order(ingredients); // Создание объекта заказа с переданными ингредиентами
    }

    @Test
    @DisplayName("Проверка отправки невалидного хэша при создании заказа")
    public void createOrderInvalidHash(){
        // Отправка запроса на создание заказа с не валидным хэшем и ожидание статуса 500 (внутренняя ошибка сервера)
        Response responseCreate = orderRequests.createOrder(order);
        responseCreate.then().log().all().statusCode(500);
    }
}
