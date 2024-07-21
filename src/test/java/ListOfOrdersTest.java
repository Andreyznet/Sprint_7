import data.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListOfOrdersTest implements Constants {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Check value orders in list of orders")
    @Description("Проверка, что количество заказов содержится в таблице заказов")
    @Test
    public void testOrdersFieldInListOfOrders() {
        given()
                .header("Content-type", "application/json")
                .when()
                .get(API_ORDER)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("orders", instanceOf(List.class)) // Проверяем, что orders - список
                .body("orders", hasSize(greaterThanOrEqualTo(1))); // Проверяем, что в списке есть заказы
    }
}
