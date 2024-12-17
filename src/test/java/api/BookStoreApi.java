package api;

import io.qameta.allure.Step;
import models.IsbnModel;
import models.AddBookListRequestModel;

import java.util.List;

import static helpers.extensions.LoginExtension.cookies;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.*;

public class BookStoreApi {

    @Step("Delete all books from the cart using API")
    public BookStoreApi deleteAllBooksInCart() {

        given(createRequestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .queryParam("UserId", cookies.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(deleteBook204Spec);
        return this;
    }

    @Step("Add book to the cart using API")
    public void addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddBookListRequestModel request = new AddBookListRequestModel(cookies.getUserId(), List.of(isbnModel));

        given(createRequestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(successfulResponse201Spec);

    }
}