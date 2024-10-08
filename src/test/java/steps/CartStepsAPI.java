package steps;

import models.BookDataModel;
import models.ResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ResponseRequestSpecs.*;

public class CartStepsAPI {

    private final ResponseModel authResponse;

    public CartStepsAPI(ResponseModel authResponse) {
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
    public void addBookAPI(String isbn) {
        String userId = authResponse.getUserId();
        BookDataModel bookData = new BookDataModel(userId, isbn);


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
