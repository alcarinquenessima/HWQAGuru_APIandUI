package componentsAPI;

import models.ResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.ResponseRequestSpecs.*;

public class CartComponent {

    private final ResponseModel authResponse;

    // Конструктор
    public CartComponent(ResponseModel authResponse) {
        this.authResponse = authResponse;
    }
    public void deleteBookAPI() {
        step("Delete all books", () ->
                given(requestSpec)
                        .header("Authorization", "Bearer " + authResponse.getToken())
                        .queryParams("UserId", authResponse.getUserId())
                        .when()
                        .delete("/BookStore/v1/Books")
                        .then()
                        .spec(responseSpec204)
        );
    }
    public void addBookAPI() {
        String userId = authResponse.getUserId();
        String isbn = "9781449365035";
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, isbn);

        step("Add book", () ->
                given(requestSpec)
                        .header("Authorization", "Bearer " + authResponse.getToken())
                        .body(bookData)
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(responseSpec201)
        );
    }
}
