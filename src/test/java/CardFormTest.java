import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardFormTest {
    @Test
    public void cardFormTestWithSelenide() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Омск");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Литвинов Артем");
        $("[data-test-id='phone'] input").setValue("+79236771616");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=notification] .notification__title").shouldHave(visible, Duration.ofSeconds(15));

    }
}
