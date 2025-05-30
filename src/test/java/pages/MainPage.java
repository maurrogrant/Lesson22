package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private final SelenideElement userHeader = $("div.nav-user");
    private final SelenideElement financialFreedomBlock = $("#accounts-can-spend span span span span");
    private final SelenideElement financialFreedomTitle = $x("//span[contains(text(), 'Финансовая свобода')]");
    private final ElementsCollection allCards = $$("div.pull-right.account-cards > a");
    private final SelenideElement cardsContainer = $("div.pull-right.account-cards");
    private final SelenideElement travelCard = $("a[data-content^='Travel']");
    private final SelenideElement goldCard = $("a[data-content^='Золотая']");

    @Override
    public MainPage isPageOpened() {
        userHeader.shouldBe(visible);
        cardsContainer.shouldBe(visible);
        return this;
    }

    public MainPage isCardsSectionDisplayed() {
        cardsContainer.shouldBe(visible);
        return this;
    }

    public void isFinancialFreedomBlockDisplayed() {
        financialFreedomTitle.shouldBe(visible);
        financialFreedomBlock.shouldBe(visible);
    }

    public String getFinancialFreedomAmount() {
        return financialFreedomBlock.text();
    }

    public boolean isFinancialFreedomAmountValid() {
        String amount = getFinancialFreedomAmount().trim();
        return amount.matches("^\\s*\\d{1,3}( \\d{3})*\\.\\d{2}");
    }

    public int getCardsCount() {
        return allCards.size();
    }

    public MainPage hoverOverTravelCard() {
        travelCard.hover();
        return this;
    }

    public MainPage hoverOverGoldCard() {
        goldCard.hover();
        return this;
    }

    public String getTravelCardTooltip() {
        return travelCard.getAttribute("data-content");
    }

    public String getGoldCardTooltip() {
        return goldCard.getAttribute("data-content");
    }

    public boolean verifyTravelCardTooltip() {
        return getTravelCardTooltip().matches("Travel \\*\\d{4}");
    }

    public boolean verifyGoldCardTooltip() {
        return getGoldCardTooltip().matches("Золотая \\*\\d{4}");
    }
}
