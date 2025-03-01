package pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrationForm {

    private final String titleText = "Student Registration Form";


    public RegistrationForm setFirstName(String firstName) {

        $("#userName-wrapper")
                .$("#firstName").setValue(firstName);

        return this;

    }

    public RegistrationForm setLastName(String lastName) {
        $("#userName-wrapper")
                .$("#lastName").setValue(lastName);

        return this;
    }

    public RegistrationForm setEmail(String email) {
        $("#userEmail-wrapper")
                .$("#userEmail").setValue(email);
        return this;
    }

    public RegistrationForm setSubject(String ...subjects) {

        String selector = "#subjectsContainer input";

        if (subjects.length == 1) {
            $(selector)
                    .setValue(subjects[0])
                    .pressEnter();
        } else {
            for (String subject : subjects) {
                $(selector)
                        .setValue(subject)
                        .pressEnter();
            }
        }
        return this;
    }

    public RegistrationForm setGenderNumber(Integer genderNumber)  {
        String selectedRadioBtn = "label[for='gender-radio-" + genderNumber + "']";
        $(selectedRadioBtn)
                .shouldBe(visible)
                .scrollTo()
                .click();
        return this;
    }

    public RegistrationForm setMobilePhone(String mobilePhone) {
        $("#userNumber-wrapper")
                .$("#userNumber").setValue(mobilePhone);
        return this;
    }

    public RegistrationForm setHobbyNumber(Integer hobbyNumber) {
        String hobby = String.format("//div[@id='hobbiesWrapper']//label[@for='hobbies-checkbox-%d']", hobbyNumber);
        $x(hobby)
                .scrollTo()
                .click();
        return this;
    }

    public RegistrationForm setPicture(String path) {
        String formFile = "//div[@class='form-file']//input";
        $x(formFile)
                .scrollTo()
                .shouldBe(visible)
                .uploadFile(
                        new java.io.File(path)
                );
        return this;
    }

    public RegistrationForm setCurrentAddress(String currentAddress) {
        String curAddress = "//div[@id='currentAddress-wrapper']//textarea";
        $x(curAddress)
                .shouldBe(visible)
                .setValue(currentAddress);

        return this;

    }

    public RegistrationForm setStateName(String stateName) {
        String stateField = "//div[@id='stateCity-wrapper']//div[@id='state']";

        $x(stateField)
                .scrollTo()
                .click();


        $x("//div[@class=' css-26l3qy-menu']")
                .$x(".//div[@tabindex='-1' and text()='" + stateName + "']")
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();

        return this;
    }


    public RegistrationForm setDatePicker(String month, String year, String day) {

        String datePicker = String.format(".react-datepicker__day--0%s", day);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(datePicker).click();

        return this;

    }


    public RegistrationForm setCityName(String cityName) {
        String cityField = "//div[@id='stateCity-wrapper']//div[@id='city']";

        $x(cityField)
                .scrollTo()
                .click();


        $x("//div[@class=' css-26l3qy-menu']")
                .$x(".//div[@tabindex='-1' and text()='" + cityName + "']")
                .shouldBe(visible, Duration.ofSeconds(3))
                .click();

        return this;
    }


    public void testModal(String firstName, String lastName, String email) {

        String fullName = firstName + " " + lastName;


        $x("//button[text()='Submit']")
                .shouldBe(clickable)
                .click();

        String actualModalText = $(".modal-content")
                .shouldBe(visible, Duration.ofSeconds(3))
                .$(".modal-header").getText();


        String firstNameTable = $x("//tr[td[text()='Student Name']]/td[2]").getText();
        String emailTable = $x("//tr[td[text()='Student Email']]/td[2]").getText();
        String expectedModalText = "Thanks for submitting the form";

        $(".modal-footer button#closeLargeModal").click();



        assertEquals(expectedModalText, actualModalText);
        assertEquals(fullName, firstNameTable);
        assertEquals(email, emailTable);

    }


    public RegistrationForm openPage() {

        open("/automation-practice-form");
        $(".practice-form-wrapper")
                .shouldHave(text(titleText));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;

    }
}
