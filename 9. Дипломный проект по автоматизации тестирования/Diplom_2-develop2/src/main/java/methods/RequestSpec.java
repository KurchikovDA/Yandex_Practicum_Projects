package methods;

import constants.ApiConstants;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

// Класс RequestSpec используется для предоставления базовой спецификации запроса (RequestSpecification),
// которая будет использоваться при выполнении запросов
public class RequestSpec {

    // Метод для создания базовой спецификации запроса
    public static RequestSpecification requestSpecification() {
        return given().log().all() // Начинаем создание запроса и логируем все действия
                .contentType(ContentType.JSON) // Устанавливаем тип контента как JSON
                .baseUri(ApiConstants.BURGERS_URL); // Устанавливаем базовый URL из ApiConstants
    }

}
