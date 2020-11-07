package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Test {
    private Faker faker;
    DataUser dataUser = new DataUser();

    @BeforeEach
    void befor() {
        open("http://localhost:9999");
    }

    public String dataInput() {
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        return inputDate;
    }

    @org.junit.jupiter.api.Test
    void getTrueInputValidForm() throws InterruptedException {
        $("[placeholder=Город]").setValue("Кр");
        $$("[class=popup__content] .menu-item__control").last().click();
        dataInput();
        String name = dataUser.dataName();
        $("[data-test-id=name].input_type_text .input__control").setValue(name);
        String phone = dataUser.dataPhone();
        $("[data-test-id=phone]").$("[name=phone]").setValue(phone);
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        Thread.sleep(1500);
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $$("[class=button__text]").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").$("[class=notification__content]")
                .shouldHave(textCaseSensitive("Встреча успешно запланирована на " + dataInput()));
    }

    @org.junit.jupiter.api.Test
    void errorExpectedWhenInputIncorrectCity() {
        $("[placeholder=Город]").setValue("Сочи");
        dataInput();
        String name = dataUser.dataName();
        $("[data-test-id=name].input_type_text .input__control").setValue(name);
        String phone = dataUser.dataPhone();
        $("[data-test-id=phone]").$("[name=phone]").setValue(phone);
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave
                (exactTextCaseSensitive("Доставка в выбранный город недоступна"));
    }

    @org.junit.jupiter.api.Test
    void errorExpectedWhenEmptyFieldDate() {
        $("[placeholder=Город]").setValue("Краснодар");
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        String name = dataUser.dataName();
        $("[data-test-id=name].input_type_text .input__control").setValue(name);
        String phone = dataUser.dataPhone();
        $("[data-test-id=phone]").$("[name=phone]").setValue(phone);
        $("[class=checkbox__box]").click();
        $$("[class=button__text]").find(exactText("Запланировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave
                (exactTextCaseSensitive("Неверно введена дата"));
    }

}
