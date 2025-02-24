import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestClass {
    private static String URL = "https://google.com";
    private static String MESSAGE = "hello world";

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
    }


    @Test
    void firstTest() {
        open(URL);
        $(".gLFyf")
                .setValue(MESSAGE)
                .pressEnter();
        $(".LC20lb.MBeuO.DKV0Md").shouldBe(visible, Duration.ofSeconds(3));

    }

}
