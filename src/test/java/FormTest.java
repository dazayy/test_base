import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

public class FormTest {


    @BeforeEach
    void setUp() {
        String URL = "https://demoqa.com/";
        open(URL);
        getWebDriver().manage().window().maximize();
    }

    @ParameterizedTest
    @DisplayName("Fill in the form")
    @CsvFileSource(resources = "data.csv", numLinesToSkip = 1)
    void testForm(
            String firstName, String lastName,
            String email, String radioFigure, String mobilePhone,
            String birthDay, String subject, String currentAddress,
            String stateName, String cityName
    ) throws InterruptedException {

        boolean existElem = false;
        String IMG_PATH = "/Users/pechalov/Desktop/course/lesson2/Lesson2/src/test/resources/image.jpg";


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
        $(selectedRadioBtn)
                .shouldBe(visible)
                .scrollTo()
                .click();

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

        String stateField = "//div[@id='stateCity-wrapper']//div[@id='state']";
        String cityField = "//div[@id='stateCity-wrapper']//div[@id='city']";

        $x(stateField)
                .click();


        $x("//div[@class=' css-26l3qy-menu']")
                .$x(".//div[@tabindex='-1' and text()='" + stateName + "']")
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();


        $x(cityField)
                .click();


        $x("//div[@class=' css-26l3qy-menu']")
                .$x(".//div[@tabindex='-1' and text()='" + cityName + "']")
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();


        $x("//button[text()='Submit']")
                .shouldBe(clickable)
                .click();


        String actualModalText = $(".modal-content")
                .shouldBe(visible, Duration.ofSeconds(3))
                .$(".modal-header").getText();

        String expectedModalText = "Thanks for submitting the form";


        $(".modal-dialog").click();


//        $(".modal-footer")
//                .$x(".//button[@id='closeLargeModal']")
//                .scrollTo()
//                .shouldBe(visible)
//                .click();

        assertEquals(expectedModalText, actualModalText);
        // Thanks for submitting the form
        Thread.sleep(30000);

    }


}
