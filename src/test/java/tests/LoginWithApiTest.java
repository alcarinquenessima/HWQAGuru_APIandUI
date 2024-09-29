package tests;

import componentsAPI.AuthComponent;
import models.AuthRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("AllTests")
@Tag("Login")
@DisplayName("Login and profile test")
public class LoginWithApiTest extends TestBase{

    AuthRequestModel authData = new AuthRequestModel();
    AuthComponent authorizationAPI = new AuthComponent();
    @Test
    @DisplayName("Successful login via API")
    public void successfulLoginWithApiTest() {
        authData.setUserName("testAcc1");
        authData.setPassword("testAcc1_!%");

        step("Authorize via API", () ->
                authorizationAPI.authorizeTestAPI());

        step("Verify username on profile page", () -> {
            open("/profile");
            $("#userName-value").shouldHave(text(authData.getUserName()));
        });
    }

}
