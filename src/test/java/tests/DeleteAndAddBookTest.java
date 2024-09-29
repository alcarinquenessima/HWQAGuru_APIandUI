package tests;

import componentsAPI.AuthComponent;
import componentsAPI.CartComponent;
import componentsUI.CartComponentUI;
import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("AllTests")
@DisplayName("Delete book from collection")
public class DeleteAndAddBookTest extends TestBase{
    @Test
    public void addBook ()
    {
        AuthComponent authorization = new AuthComponent();
        ResponseModel authResponse = authorization.authorizeTestAPI();

        CartComponent cartActions = new CartComponent(authResponse);

        authorization.authorizeTestAPI();
        cartActions.addBookAPI();

    }
    @Test
    @Tag("DeleteBookAPI")
    public void deleteBookFromCollectionAPI ()
    {
        AuthComponent authorization = new AuthComponent();
        ResponseModel authResponse = authorization.authorizeTestAPI();

        CartComponent cartActions = new CartComponent(authResponse);

        authorization.authorizeTestAPI();
        cartActions.deleteBookAPI();
        cartActions.addBookAPI();
        cartActions.deleteBookAPI();
    }

    @Tag("Delete_book_with_API")
    @Test
    public void deleteBookFromCollection ()
    {
        AuthComponent authorization = new AuthComponent();
        CartComponentUI cartActionsUI = new CartComponentUI();
        ResponseModel authResponse = authorization.authorizeTestAPI();

        CartComponent cartActions = new CartComponent(authResponse);

        step("API actions", () -> {
            authorization.authorizeTestAPI();
            cartActions.deleteBookAPI();
            cartActions.addBookAPI();
        });
        step("UI actions", () -> {
        cartActionsUI.openCart()
                     .checkBookInCart()
                     .deleteBookInCart()
                     .confirmDeleteBook();
        });
    }
}


