package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Test {
    DataUser dataUser = new DataUser();

    @BeforeEach
    void befor() {
        open("http://localhost:9999");
    }


    @org.junit.jupiter.api.Test
    void getTrueInputValidForm() throws InterruptedException {
        dataUser.cityInput();
        dataUser.dataInput(6);
        dataUser.dataNamePhone();
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + dataUser.dataInput(6)));
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $$("[class=button__text]").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + dataUser.dataInput(6)));
    }

    @org.junit.jupiter.api.Test
    void errorExpectedWhenInputIncorrectCity() {
        dataUser.cityNoVal();
        dataUser.dataInput(6);
        dataUser.dataNamePhone();
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactTextCaseSensitive("Доставка в выбранный город недоступна"));
    }

    @org.junit.jupiter.api.Test
    void errorExpectedWhenEmptyFieldDate() {
        dataUser.cityInput();
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        dataUser.dataNamePhone();
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave
                (exactTextCaseSensitive("Неверно введена дата"));
    }

}
