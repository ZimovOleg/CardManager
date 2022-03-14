package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTestSelenide {

    // Верное заполнение всех полей
    @Test
    public void shouldСorrectFilling() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("Летов Олег");
        $("[type='tel']").setValue("+88002000500");
        $(".checkbox").click();
        $(".button").click();
        $(".paragraph_theme_alfa-on-white").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // Неверное заполнение поля "Фамилия и имя"
    @Test
    public void shouldErrorIfIncorrectName() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("Letof Oleg");
        $("[type='tel']").setValue("+88002000500");
        $(".checkbox").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // Поле "Фамилия и имя" не заполненно
    @Test
    public void shouldErrorNullName() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("");
        $("[type='tel']").setValue("+88002000500");
        $(".checkbox").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // Верное заполнение поля "Фамилия и имя" через дефис
    @Test
    public void shouldСorrectFillingNameWithHyphen() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("Жак-Ив Кусто");
        $("[type='tel']").setValue("+88002000500");
        $(".checkbox").click();
        $(".button").click();
        $(".paragraph_theme_alfa-on-white").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // поле "Телефон" не заполенно
    @Test
    public void shouldErrorNullPhoneNumber() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("Жак-Ив Кусто");
        $("[type='tel']").setValue("");
        $(".checkbox").click();
        $(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // Неверное заполнение поля "Телефон"
    @Test
    public void shouldErrorIfIncorrectPhoneNumber() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("Жак-Ив Кусто");
        $("[type='tel']").setValue("88002000500");
        $(".checkbox").click();
        $(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldErrorClickCheckbox() {
        open ("http://localhost:9999/");
        $("[type='text']").setValue("Жак-Ив Кусто");
        $("[type='tel']").setValue("+78002000500");
        $(".button").click();
        $(".input_invalid").should(exist);
    }

}
