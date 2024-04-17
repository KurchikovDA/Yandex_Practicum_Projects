import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import order.Client;
import order.Order;
import order.OrderStepMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.hamcrest.CoreMatchers.*;


@RunWith(Parameterized.class)
public class CreateOrderTest extends Client {
    // Задаю статические параметры заказа
    private static final String FIRST_NAME = "Артас";
    private static final String LAST_NAME = "Менетил";
    private static final String ADDRESS = "Азерот, г. Лордерон, ул. Тронный Зал";
    private static final String METRO_STATION = "Черкизовская";
    private static final String PHONE = "+79998886677";
    private static final int RENT_TIME = 6;
    private static final String DELIVERY_DATE = "2024-04-04";
    private static final String COMMENT = "За моего Отца!";

    // Параметры для цветов заказа
    private final String color;
    String track;

    @Before
    // Устанавливаю предварительно настроенную спецификацию запроса в RestAssured перед каждым запуском теста.
    public void setUp() {
        RestAssured.requestSpecification = requestSpec;
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
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, new String[]{color});
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
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT);
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
