package tests;

import steps.AuthStepsAPI;
import steps.CartStepsAPI;
import pages.ProfilePage;
import models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("AllTests")
@DisplayName("Delete book from collection")
public class DeleteAndAddBookTest extends TestBase{
    String isbn = System.getProperty("BOOK_ISBN", "9781449365035");

    @Test
    public void addBook ()
    {
        AuthStepsAPI authorization = new AuthStepsAPI();
        ResponseModel authResponse = authorization.authorizeTestAPI();

        CartStepsAPI cartActions = new CartStepsAPI(authResponse);

        authorization.authorizeTestAPI();
        cartActions.addBookAPI(isbn);

    }
    @Test
    @Tag("DeleteBookAPI")
    public void deleteBookFromCollectionAPI ()
    {
        AuthStepsAPI authorization = new AuthStepsAPI();
        ResponseModel authResponse = authorization.authorizeTestAPI();

        CartStepsAPI cartActions = new CartStepsAPI(authResponse);

        authorization.authorizeTestAPI();
        cartActions.deleteBookAPI();
        cartActions.addBookAPI(isbn);
        cartActions.deleteBookAPI();
    }

    @Tag("Delete_book_with_API_and_UI")
    @Test
    public void deleteBookFromCollection ()
    {
        AuthStepsAPI authorization = new AuthStepsAPI();
        ProfilePage cartActionsUI = new ProfilePage();
        ResponseModel authResponse = authorization.authorizeTestAPI();

        CartStepsAPI cartActions = new CartStepsAPI(authResponse);

        step("API actions", () -> {
            cartActions.deleteBookAPI();
            cartActions.addBookAPI(isbn);
        });
        step("UI actions", () -> {
        cartActionsUI.openCart()
                     .checkBookInCart(isbn)
                     .deleteBookInCart()
                     .confirmDeleteBook();
        });
    }
}


