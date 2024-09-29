package tests;

import models.AuthRequestModel;
import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ResponseRequestSpecs.requestSpec;
import static specs.ResponseRequestSpecs.responseSpec200;

@Tag("Login")
@DisplayName("Login and profile test")
public class LoginWithApiTest extends TestBase{

    AuthRequestModel authData = new AuthRequestModel();
    @Test
    @DisplayName("Successful login via API")
    public void successfulLoginWithApiTest() {
        authData.setUserName("testAcc1");
        authData.setPassword("testAcc1_!%");

        ResponseModel authResponse = step("Authorize via API", () ->
                given(requestSpec)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(ResponseModel.class)


        );

        step("Set cookies and open profile page", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        });

        step("Verify username on profile page", () -> {
            open("/profile");
            $("#userName-value").shouldHave(text(authData.getUserName()));
        });
    }

}
