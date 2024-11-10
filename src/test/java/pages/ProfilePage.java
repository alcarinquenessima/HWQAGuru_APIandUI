package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement deleteButton = $("#delete-record-undefined");
    private final SelenideElement confirmDeleteButton = $("#closeSmallModal-ok");

    @Step ("Gey book isbn")
    public SelenideElement getUserBook(String isbn) {
        return $("a[href='/profile?book=" + isbn + "']");
    }
    @Step ("Open user cart")
    public ProfilePage openCart (){
        open("/profile");
        return this;
    }

    @Step ("Check book in cart")
    public ProfilePage checkBookInCart (String isbn){
        getUserBook(isbn).shouldBe(visible);
        return this;
    }

    @Step ("Delete book")
    public ProfilePage deleteBookInCart (){
        deleteButton.click();
        return this;
    }

    @Step ("Confirm deletion of the book")
    public void confirmDeleteBook (){
        confirmDeleteButton.click();
    }
}
