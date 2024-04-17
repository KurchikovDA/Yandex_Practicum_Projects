package orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import order.OrderStepMethods;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static constants.ApiConstants.SCOOTER_URL;

//Класс теста для проверки того, что в тело ответа возвращается список заказов.
public class GetOrderListTest {
    // Создаю приватное поле orderStepMethods типа OrderStepMethods
    private OrderStepMethods orderStepMethods;

    @Before
    public void setUp() {
        // Устанавливаю базовый URI перед каждым тестом
        RestAssured.baseURI = SCOOTER_URL;
        // Инициализирую экземпляр класса, содержащего методы для работы с заказами
        orderStepMethods = new OrderStepMethods();
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка, что в тело ответа возвращается список заказов.")
    public void getOrderList() {
        // Выполняю запрос на получение списка заказов
        ValidatableResponse responseCreate = orderStepMethods.getOrderList();
        // Извлекаю статус код из ответа
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        // Извлекаю тело ответа в виде списка HashMap
        List<HashMap> orderBody = responseCreate.extract().path("orders");
        // Проверяю, что статус код равен 200
        Assert.assertEquals(200, actualStatusCodeCreate);
        // Проверяю, что тело ответа не пусто
        Assert.assertNotNull(orderBody);
    }
}
