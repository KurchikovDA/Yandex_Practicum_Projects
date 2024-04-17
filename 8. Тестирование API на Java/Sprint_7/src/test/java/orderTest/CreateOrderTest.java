package orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import order.Order;
import order.OrderStepMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static constants.ApiConstants.SCOOTER_URL;
import static org.hamcrest.CoreMatchers.*;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    // Задаю статические параметры заказа
    private static final String firstName = "Артас";
    private static final String lastName = "Менетил";
    private static final String address = "Азерот, г. Лордерон, ул. Тронный Зал";
    private static final String metroStation = "Черкизовская";
    private static final String phone = "+79998886677";
    private static final int rentTime = 6;
    private static final String deliveryDate = "2024-04-04";
    private static final String comment = "За моего Отца!";

    // Параметры для цветов заказа
    private final String color;
    String track;

    // Перед каждым тестом устанавливаю базовый URI
    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    // Конструктор класса, используемый параметризованным тестом
    public CreateOrderTest(String color) {
        this.color = color;
    }

    // Параметры для теста, определяющие возможные цвета заказа
    @Parameterized.Parameters(name = "colour = ''{0}''")  // {0} - это место, куда подставляется значение параметра.
    public static Object[] getColour() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK, GREY"},
                {""}
        };
    }

    // Тест для создания заказа с указанием цвета
    @Test
    @DisplayName("Создание заказа")
    @Description("Заказ можно создать с указанием только одного цвета или обоих цветов")
    public void createOrder() {
        // Создаю заказ с указанным цветом
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, new String[]{color});
        // Выполняю запрос на создание заказа
        Response response = OrderStepMethods.createOrder(order);
        // Извлекаю трек заказа из ответа
        track = response.then().extract().path("track").toString();
        // Проверяю, что заказ успешно создан
        response.then().assertThat().statusCode(201).and().assertThat().body("track", notNullValue());
    }

    // Тест для создания заказа без указания цвета
    @Test
    @DisplayName("Создание заказа без указания параметра color")
    @Description("Заказ можно создать, если не указать параметр color")
    public void createOrderWithoutColor() {
        // Создаю заказ без указания цвета
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
        // Выполняю запрос на создание заказа
        Response response = OrderStepMethods.createOrder(order);
        // Извлекаю трек заказа из ответа
        track = response.then().extract().path("track").toString();
        // Проверяю, что заказ успешно создан
        response.then().assertThat().statusCode(201).and().assertThat().body("track", notNullValue());
    }

    // После выполнения теста отменяем созданный заказ
    @After
    public void cancelOrder() {
        OrderStepMethods.cancelOrder(track);
    }
}
