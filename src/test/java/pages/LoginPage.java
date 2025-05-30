package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {

    private final SelenideElement btnLogin = $("#login-button");
    private final SelenideElement inputUsername = $("#username");
    private final SelenideElement inputPassword = $("input[name='password']");

    @Override
    public LoginPage isPageOpened() {
        btnLogin.shouldBe(visible);
        return this;
    }

    public LoginPage navigateTo() {
        navigate("https://idemo.bspb.ru");
        return this.isPageOpened();
    }

    public LoginPage fillUsername(String username) {
        inputUsername.setValue(username);
        return this;
    }

    public LoginPage fillPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    public TwoFactorAuthPage submitLogin() {
        btnLogin.click();
        return page(TwoFactorAuthPage.class).isPageOpened();
    }

    public TwoFactorAuthPage performLogin(String username, String password) {
        return fillUsername(username)
                .fillPassword(password)
                .submitLogin();
    }
}
