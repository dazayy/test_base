import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {


    @ParameterizedTest
    @DisplayName("Fill in the form")
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 1)
    void testForm(
            String firstName, String lastName,
            String email, String radioFigure, String mobilePhone,
            String Date, String subject, String currentAddress
    ) {

        boolean existElem = false;
        String URL = "https://demoqa.com/";


        open(URL);
        $$(".card.mt-4.top-card")
                .findBy(text("Forms"))
                .scrollTo()
                .shouldBe(visible, Duration.ofSeconds(3)).click();

        existElem = $$(".element-list").findBy(text("Practice Form")).exists();

        if (!existElem) {
            $$(".header-text")
                    .findBy(text("Forms"))
                    .shouldBe(visible, Duration.ofSeconds(3)).click();
        }

        $$(".element-list")
                .findBy(text("Practice Form"))
                .shouldBe(visible)
                .click();



        $("#userName-wrapper")
                .$("#firstName").setValue(firstName);

        $("#userName-wrapper")
                .$("#lastName").setValue(lastName);


        $("#userEmail-wrapper")
                .$("#userEmail").setValue(email);

        String selectedRadioBtn = "input#gender-radio-1" + radioFigure;

        /*
        *
        * I have a problem with finding a right element (radion button)
        *
        *
        * */

        $("div#genterWrapper")
                .$(selectedRadioBtn)
                .shouldBe(visible)
                .click();




    }


}
