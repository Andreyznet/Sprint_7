import clients.OrdersClient;
import data.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

import lombok.*;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class CreateOrderTest {

    private final List<String> colorValue;

    @Parameterized.Parameters
    public static Collection<Object[]> getTestDataCreateOrder() {
        return Arrays.asList(new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {null},
                {List.of("GREY", "BLACK")}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Проверка что заказ создан и статус - код: 201 с помощью параметризированных тестов ")
    @Test
    public void testTrackFieldInOrder() {
        OrdersClient ordersClient = new OrdersClient();
        ValidatableResponse emptyPasswordField = ordersClient.getOrdersResponse(
                new Order("Anton", "Bekker", "London", 4, "+7 800 123 45 67", 5, "2024-06-06", "Пожалуйста принесите Чёрный", colorValue));
        emptyPasswordField
                .statusCode(201);
        MatcherAssert.assertThat("track", notNullValue());
    }
}