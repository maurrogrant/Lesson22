package pages;

import com.codeborne.selenide.Selenide;

public abstract class BasePage {
    public abstract BasePage isPageOpened();

    protected void navigate(String url) {
        Selenide.open(url);
    }
}
