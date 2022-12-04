import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardFormTest {
    public String genDate(long numDay, String pattern) {
        return LocalDate.now().plusDays(numDay).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void cardFormTestWithSelenide() {
        open("http://localhost:9999");
        String planDate = genDate(6, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Омск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planDate);
        $("[data-test-id='name'] input").setValue("Литвинов Артем");
        $("[data-test-id='phone'] input").setValue("+79236771616");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=notification] .notification__title").shouldHave(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(text("Встреча успешно забронирована на " + planDate)).shouldHave(visible, Duration.ofSeconds(15));

    }
}
