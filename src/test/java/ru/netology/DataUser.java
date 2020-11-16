package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class DataUser {
    //    static Faker faker;
    static Faker faker = new Faker(new Locale("ru"));

    private DataUser() {
    }

    //    @Value
//    static class VerifData {
//        private String name;
//        private String phone;
//
//    }
    static String cityForInput() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String city[] = {"Екатеринбург", "Йошкар-Ола", "Калининград", "Кемерово", "Киров", "Кострома", "Краснодар",
                "Красноярск", "Курган", "Курск", "Санкт-Петербург", "Сыктывкар", "Чебоксары"};
        return city[rand];
    }

    static String cityNoVal() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String cityNoVal[] = {"Мегион", "Кизляр", "Урус-Мартан", "Снежинск", "Кингисепп", "Заринск", "Курганинск"};
        return cityNoVal[rand];
    }


    static String cityWishLetterEBrief() {
        Random random = new Random();
        int rand = random.nextInt(12);
        String cityNoVal[] = {"Будёновск", "Кизляр", "Урус-Мартан", "Снежинск", "Кингисепп", "Заринск", "Курганинск"};
        return cityNoVal[rand];
    }

    static String dataPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    static String dataName() {
        String name = faker.name().lastName();
        name = name + " " + faker.name().firstName();
        return name;
    }

    static String dataInput(int days) {
        String inputDate = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        return inputDate;
    }
//    void city_Input() {
//        Random random = new Random();
//        $("[placeholder=Город]").setValue("Кр");
//        var size = $$("[class=popup__content] .menu-item__control").size();
//        int rand = random.nextInt(size);
//        $$("[class=popup__content] .menu-item__control").get(rand).click();
//    }


}
