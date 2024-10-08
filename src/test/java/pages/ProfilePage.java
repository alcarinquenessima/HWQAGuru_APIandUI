package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement userBook = $("a[href='/profile?book=9781449365035']");
    private final SelenideElement deleteButton = $("#delete-record-undefined");
    private final SelenideElement confirmDeleteButton = $("#closeSmallModal-ok");

    @Step ("Open user cart")
    public ProfilePage openCart (){
        open("/profile");
        return this;
    }

    @Step ("Check book in cart")
    public ProfilePage checkBookInCart (){
        userBook.shouldHave(text("Speaking JavaScript"));
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
