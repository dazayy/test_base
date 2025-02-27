import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

public class FormTest {

    @BeforeEach
    void setUp() {

        boolean existElem = false;
        String URL = "https://demoqa.com/";

        open(URL);
        getWebDriver().manage().window().maximize();


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

    }



    @ParameterizedTest
    @DisplayName("Test Name block")
    @CsvFileSource(resources = "testData/names.csv", numLinesToSkip = 1)
    void testNameBlock(String firstName, String lastName) {

        $("#userName-wrapper")
                .$("#firstName").setValue(firstName);

        $("#userName-wrapper")
                .$("#lastName").setValue(lastName);

    }


    @ParameterizedTest
    @DisplayName("Test Email block")
    @CsvFileSource(resources = "testData/emails.csv", numLinesToSkip = 1)
    void testEmailBlock(String email) {
        $("#userEmail-wrapper")
                .$("#userEmail").setValue(email);

    }


    @ParameterizedTest
    @DisplayName("Test Gender block")
    @CsvFileSource(resources = "testData/genders.csv", numLinesToSkip = 1)
    void testGenderBlock(Integer genderNumber) {
        String selectedRadioBtn = "label[for='gender-radio-" + genderNumber + "']";
        $(selectedRadioBtn)
                .shouldBe(visible)
                .scrollTo()
                .click();
    }


    @ParameterizedTest
    @DisplayName("Test Phone Number block")
    @CsvFileSource(resources = "testData/mobilePhones.csv", numLinesToSkip = 1)
    void testPhoneNumberBlock(String mobilePhone) {
        $("#userNumber-wrapper")
                .$("#userNumber").setValue(mobilePhone);
    }


    @ParameterizedTest
    @DisplayName("Test Hobby block")
    @CsvFileSource(resources = "testData/hobbies.csv", numLinesToSkip = 1)
    void testHobbyBlock(Integer hobbyNumber) {
        String hobby = String.format("//div[@id='hobbiesWrapper']//label[@for='hobbies-checkbox-%d']", hobbyNumber);
        $x(hobby)
                .scrollTo()
                .click();
    }


    @ParameterizedTest
    @DisplayName("Test Picture block")
    @CsvFileSource(resources = "testData/images.csv", numLinesToSkip = 1)
    void testPictureBlock(String path) {
        String formFile = "//div[@class='form-file']//input";
        $x(formFile)
                .scrollTo()
                .shouldBe(visible)
                .uploadFile(
                        new java.io.File(path)
                );
    }


    @ParameterizedTest
    @DisplayName("Test Current Address block")
    @CsvFileSource(resources = "testData/currentAddress.csv", numLinesToSkip = 1)
    void testCurrentAddressBlock(String currentAddress) {
        String curAddress = "//div[@id='currentAddress-wrapper']//textarea";
        $x(curAddress)
                .shouldBe(visible)
                .setValue(currentAddress);

    }


    @ParameterizedTest
    @DisplayName("Test State and City block")
    @CsvFileSource(resources = "testData/stateCity.csv", numLinesToSkip = 1)
    void testStateAndCityBlock(String stateName, String cityName) {
        String stateField = "//div[@id='stateCity-wrapper']//div[@id='state']";
        String cityField = "//div[@id='stateCity-wrapper']//div[@id='city']";

        $x(stateField)
                .scrollTo()
                .click();


        $x("//div[@class=' css-26l3qy-menu']")
                .$x(".//div[@tabindex='-1' and text()='" + stateName + "']")
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();


        $x(cityField)
                .scrollTo()
                .click();


        $x("//div[@class=' css-26l3qy-menu']")
                .$x(".//div[@tabindex='-1' and text()='" + cityName + "']")
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();
    }


    @AfterAll
    @DisplayName("Modal test")
    static void testModal() {

        $x("//button[text()='Submit']")
                .shouldBe(clickable)
                .click();

        String actualModalText = $(".modal-content")
                .shouldBe(visible, Duration.ofSeconds(3))
                .$(".modal-header").getText();

        String expectedModalText = "Thanks for submitting the form";
        $(".modal-dialog").click();
        assertEquals(expectedModalText, actualModalText);

    }


    @ParameterizedTest
    @DisplayName("Entire Test")
    @CsvFileSource(resources = "testData/generalData.csv", numLinesToSkip = 1)
    void testEntireForm(
            String firstName,
            String lastName,
            String email,
            int gender,
            int hobby,
            String mobile,
            String state,
            String city
    ) {

        String path = "";
        String currentState = state + " " + city;

        testNameBlock(firstName, lastName);
        testEmailBlock(email);
        testPhoneNumberBlock(mobile);
        testGenderBlock(gender);
        testHobbyBlock(hobby);

        if (!path.isEmpty()) {
            testPictureBlock(path);
        }

        testCurrentAddressBlock(currentState);
        testStateAndCityBlock(state, city);
        testModal();

    }


}
