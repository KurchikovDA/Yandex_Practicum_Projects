package courier;

import static constants.ApiConstants.*;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CourierStepMethods {
    //Метод создания курьера
    @Step("Создание курьера")
public static Response createCourier(Courier courier) {
        // Отправляю POST-запрос на ручку COURIER с указанием заголовка и тела запроса (данные курьера)
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER);
    }

    //Метод для авторизации курьера
    @Step("Авторизация курьера")
    public static Response loginCourier(Courier courier) {
    // Отправляю POST-запрос на ручку LOGIN с указанием заголовка и тела запроса (данные курьера)
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN);
    }

    //Метод по удалению курьера по его id
    @Step("Удаление курьера")
    public static void deleteCourier(String courierId) {
        // Отправляю DELETE-запрос на ручку COURIER с указанием идентификатора курьера
        given()
                .delete(COURIER + "{courierId}", courierId);
    }

}
