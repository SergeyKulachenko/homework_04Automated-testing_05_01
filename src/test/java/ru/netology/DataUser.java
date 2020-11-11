package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data
@RequiredArgsConstructor
public class DataUser {
    Faker faker = new Faker(new Locale("ru"));

    @Value
    public static class VerifData {
        private String name;
        private String phone;
    }

    void cityNoVal() {
        String cityNoVal = faker.address().city();
        $("[placeholder=Город]").setValue(cityNoVal);
    }

    void cityInput() {
        Random random = new Random();
        $("[placeholder=Город]").setValue("Кр");
        var size = $$("[class=popup__content] .menu-item__control").size();
        int rand = random.nextInt(size);
        $$("[class=popup__content] .menu-item__control").get(rand).click();
    }

    void dataNamePhone() {

        String name = faker.name().lastName();
        name = name + " " + faker.name().firstName();
        $("[data-test-id=name].input_type_text .input__control").setValue(name);
        String phone = faker.phoneNumber().phoneNumber();
        $("[data-test-id=phone]").$("[name=phone]").setValue(phone);
    }


    public String dataInput(int days) {
        String inputDate = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        SelenideElement data = $("[data-test-id=date]");
        data.$("[value]").doubleClick().sendKeys(Keys.BACK_SPACE);
        data.$("[placeholder]").setValue(inputDate);
        return inputDate;
    }

}
