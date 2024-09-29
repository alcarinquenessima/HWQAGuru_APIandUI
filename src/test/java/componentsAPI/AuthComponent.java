package componentsAPI;

import models.AuthRequestModel;
import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ResponseRequestSpecs.*;


@Tag("AddBook")
@DisplayName("Add book to the collection")
public class AuthComponent extends TestBase {

    AuthRequestModel authData = new AuthRequestModel();
    private ResponseModel authResponse;

    public ResponseModel authorizeTestAPI() {

        authData.setUserName("testAcc1");
        authData.setPassword("testAcc1_!%");

        authResponse = step("Authorize via API", () ->
                given(requestSpec)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(ResponseModel.class)
        );
        step("Open icon page", () -> {
            open("/favicon.ico");
        });
        step("Set cookies and open profile page", () -> {
            getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        });
        return authResponse; // Возвращаем authResponse
    }
}
