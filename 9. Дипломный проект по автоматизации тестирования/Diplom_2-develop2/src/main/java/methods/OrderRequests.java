package methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Order;

import static constants.ApiConstants.INGREDIENTS;
import static constants.ApiConstants.ORDERS;
import static io.restassured.RestAssured.given;

public class OrderRequests {

    @Step("Получить данные об ингредиентах")
    public Response getIngredient(){ // Объявление метода getIngredient
        return given() // Возврат результата метода given
                .get(INGREDIENTS); // Вызов метода get с константой INGREDIENTS
    }

    @Step("Создать заказ")
    public Response createOrder(Order order){
        return given() // Возврат результата метода given
                .header("Content-type", "application/json") // Установка заголовка Content-type
                .and() // Метод and для читаемости цепочки вызовов
                .body(order) // Установка тела запроса как объект order
                .when() // Метод when для читаемости цепочки вызовов
                .post(ORDERS); // Вызов метода post с константой ORDERS
    }

    @Step("Получить заказы конкретного авторизованного пользователя")
    public Response getOrderUser(String token){ // Объявление метода getOrderUser с параметром token типа String
        return given() // Возврат результата метода given
                .header("Authorization", token) // Установка заголовка Authorization с переданным токеном
                .get(ORDERS); // Вызов метода get с константой ORDERS
    }

    @Step("Получить заказы не авторизованного пользователя")
    public Response getOrderUserWithoutAuthorization(){ // Объявление метода getOrderUserWithoutAuthorization
        return given() // Возврат результата метода given
                .get(ORDERS); // Вызов метода get с константой ORDERS
    }
}
