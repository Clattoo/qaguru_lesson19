package api;

import data.AuthData;
import io.qameta.allure.Step;
import models.GetBookListModel;
import models.LoginResponseModel;
import models.LoginRequestModel;

import static helpers.extensions.LoginExtension.cookies;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.*;

public class AccountApi {

    @Step("Make login request and write down response using API")
    public static LoginResponseModel getAuthorizationCookie() {
        LoginResponseModel response;
        LoginRequestModel request = new LoginRequestModel(System.getProperty("loginUser", AuthData.USER_NAME),
                System.getProperty("passwordUser", AuthData.PASSWORD));


        response = given(createRequestSpec)
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(authUserResponse200Spec)
                .extract().as(LoginResponseModel.class);

        return response;
    }

    @Step("Receive list of books using API")
    public static GetBookListModel getListOfBooks() {
        GetBookListModel response;
        response = given(createBookStoreRequestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .queryParam("UserId", cookies.getUserId())
                .when()
                .get("/Account/v1/User/" + cookies.getUserId())
                .then()
                .spec(authUserResponse200Spec)
                .extract().as(GetBookListModel.class);

        return response;
    }


}