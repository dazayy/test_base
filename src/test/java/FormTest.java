import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationForm;

public class FormTest {


    RegistrationForm registrationForm = new RegistrationForm();

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
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
    ) throws InterruptedException {

        String path = "";
        String currentState = state + " " + city;

        registrationForm
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGenderNumber(gender)
                .setMobilePhone(mobile)
                .setDatePicker("June", "1992", "18")
                .setSubject("math", "History")
                .setHobbyNumber(hobby)
                .setPicture("")
                .setCurrentAddress(currentState)
                .setStateName(state)
                .setCityName(city)
                .testModal(firstName, lastName, email);
       
    }


}
