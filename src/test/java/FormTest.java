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
        String IMG_PATH = "PUT IMG PATH";

        open(URL);
        $$(".card.mt-4.top-card")
                .findBy(text("Forms"))
                .scrollTo()
                .shouldBe(visible, Duration.ofSeconds(3)).click();

        existElem = $$(".element-list")
                .findBy(text("Practice Form"))
                .exists();

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

        String hoobie = String.format("//div[@id='hobbiesWrapper']//label[@for='hobbies-checkbox-%d']", 2);
        $x(hoobie)
                .scrollTo()
                .click();

        String formFile = "//div[@class='form-file']//input";
        $x(formFile)
                .scrollTo()
                .shouldBe(visible)
                .uploadFile(
                new java.io.File(IMG_PATH)
        );

        String curAddress = "//div[@id='currentAddress-wrapper']//textarea";
        $x(curAddress)
                .shouldBe(visible)
                        .setValue(currentAddress);

        String state = "//div[@id='stateCity-wrapper']//div[@id='state']";
        String city = "//div[@id='stateCity-wrapper']//div[@id='city']";

//        $x(state).click();
//        $x(state).sendKeys("Hary");
//        $x(state)
//                .shouldHave(text("Hary"))
//                .pressEnter();
//
//
//
//
//
//        $(".css-1uccc91-singleValue").shouldHave(text("Your State"));
        Thread.sleep(3000);

    }


}


/*
*
* <div class=" css-26l3qy-menu"><div class=" css-11unzgr">  !!!!! wrapper for dropdown menu
*
*
* <div class=" css-1n7v3ny-option" id="react-select-3-option-0" tabindex="-1">NCR</div> example of the element
* 
*
*
*  react-select-3-option-1 (id for elements) (i need to get all elements by this id but it must contain:
*                                               react-select-3-option-
* to i can get all elements and then put figure to specify my choose
*
*
*
* */



//
//<div class=" css-26l3qy-menu"><div class=" css-11unzgr">  !!!!! wrapper for dropdown menu
//<div class=" css-1n7v3ny-option" id="react-select-3-option-0" tabindex="-1">NCR</div>
//


// react-select-3-option-1


//
//<div class=" css-yt9ioa-option" id="react-select-3-option-1" tabindex="-1">Uttar Pradesh</div>
//<div class=" css-yt9ioa-option" id="react-select-3-option-2" tabindex="-1">Haryana</div>
//<div class=" css-9gakcf-option" id="react-select-3-option-3" tabindex="-1">Rajasthan</div>
//</div></div>