import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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
            String birthDay, String subject, String currentAddress
    ) throws InterruptedException {

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

        String selectedRadioBtn = "label[for='gender-radio-" + radioFigure + "']";
        $(selectedRadioBtn).shouldBe(visible).click();


        $("#userNumber-wrapper")
                .$("#userNumber").setValue(mobilePhone);


        SelenideElement dateField = $("#dateOfBirth")
                .$("#dateOfBirthInput")
                .scrollTo()
                .shouldBe(visible);

//        Selenide.executeJavaScript("arguments[0].value = '" + birthDay + "';", dateField);

//
//        SelenideElement subjectElement = $("#subjectsWrapper")
//                .$("#subjectsContainer");
//        subjectElement.click();
//        subjectElement.$("span").setValue(subject);




        Thread.sleep(30000);


    }


}
