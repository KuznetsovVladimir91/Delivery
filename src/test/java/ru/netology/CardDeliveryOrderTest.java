package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.LocalDate.now;

public class CardDeliveryOrderTest {


    @Test
    void shouldSubmitForm() {
        Configuration.holdBrowserOpen = true;


        open("http://localhost:9999");
        $("[placeholder=Город]").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String meetingDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(meetingDate);
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+79531453278");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(text("Успешно! Встреча успешно забронирована на " + meetingDate), Duration.ofSeconds(15));

    }

}
