package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    @Step("Open profile page")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Delete one book from the basket using API")
    public ProfilePage deleteOneBook() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
        Selenide.refresh();

        return this;
    }

    @Step("Assert that previous book was successfully deleted from the basket using UI")
    public void checkDeleteBookWithUI() {
        $("#see-book-Git Pocket Guide").shouldNotBe(visible);
    }
}