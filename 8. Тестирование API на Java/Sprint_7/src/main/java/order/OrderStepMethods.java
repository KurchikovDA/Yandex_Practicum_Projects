package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static constants.ApiConstants.CANCEL_ORDER;
import static constants.ApiConstants.ORDER;
import static io.restassured.RestAssured.given;

// Класс OrderStepMethods, наследуемый от Client, содержит методы для работы с заказами в API Яндекс.Самокат.
public class OrderStepMethods extends Client {

    // Метод для создания заказа, принимает объект Order и возвращает Response.
    @Step ("Создание заказа")
    public static Response createOrder(Order order) {
        return given()
                .header("Content-Type", "application/json")  // Устанавливает заголовок Content-Type в application/json
                .and()
                .body(order)  // Устанавливает тело запроса, используя объект Order
                .when()
                .post(ORDER);  // Выполняет POST-запрос на указанную ручку (ORDER) и возвращает Response.
    }

    // Метод для получения списка заказов, использует спецификацию из класса Client и возвращает ValidatableResponse.
    @Step ("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getSpec())  // Использует базовую спецификацию запроса из класса Client
                .when()
                .get(ORDER)  // Выполняет GET-запрос на ручку (ORDER)
                .then();  // Возвращает объект ValidatableResponse для валидации ответа.
    }

    // Метод для отмены заказа, принимает идентификатор заказа и выполняет PUT-запрос.
    @Step("Отмена заказа")
    public static void cancelOrder(String track) {
        given()
                .put(CANCEL_ORDER + "{track}", track); // Выполняет PUT-запрос с идентификатором заказа.
    }

}
