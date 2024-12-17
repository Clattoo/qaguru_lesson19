package tests;

import api.AccountApi;
import api.BookStoreApi;
import helpers.extensions.WithLogin;
import models.GetBookListModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("demoqa_api")

@DisplayName("Testing Book Store on demoqa.com")
public class DemoTests extends TestBase {

    BookStoreApi bookStoreApi = new BookStoreApi();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @DisplayName("Check that the book from the basket successfully delete")
    void successfulDeleteBookTest() {

        bookStoreApi.deleteAllBooksInCart()
        .addBookToList("9781449325862");
        profilePage.openPage()
                .deleteOneBook()
                .checkDeleteBookWithUI();


        GetBookListModel response = AccountApi.getListOfBooks();
        assertThat(response.getBooks()).isEmpty();

    }
}