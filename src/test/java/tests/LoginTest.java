package tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import config.ConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoginPage;
import pages.MainPage;
import pages.TwoFactorAuthPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ScreenShooterExtension.class})
public class LoginTest {

    @BeforeAll
    public static void init() {
        ConfigTest.initConfig();
    }

    @Test
    public void testFullScenario() {

        TwoFactorAuthPage twoFactorAuthPage = new LoginPage()
                .navigateTo()
                .performLogin("demo", "demo");

        MainPage mainPage = twoFactorAuthPage.completeAuth("0000");

        mainPage.isCardsSectionDisplayed()
                .isFinancialFreedomBlockDisplayed();

        assertTrue(mainPage.isFinancialFreedomAmountValid(),
                "Сумма в блоке 'Финансовая свобода' не соответствует формату");
        System.out.println("Сумма финансовой свободы: " + mainPage.getFinancialFreedomAmount());
        assertTrue(mainPage.getCardsCount() >= 3, "Отсутствует секция с картами");
        assertTrue(mainPage.hoverOverTravelCard().verifyTravelCardTooltip(),
                "Неправильный формат всплывающей подсказки по карте Travel");
        System.out.println("Карта Travel: " + mainPage.getTravelCardTooltip());
        assertTrue(mainPage.hoverOverGoldCard().verifyGoldCardTooltip(),
                "Неправильный формат всплывающей подсказки по карте Gold");
        System.out.println("Карта Gold: " + mainPage.getGoldCardTooltip());

    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
