package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TwoFactorAuthPage extends BasePage {

    private final SelenideElement otpCodeField = $("#otp-code");
    private final SelenideElement btnLogininOtp = $("#login-otp-button");
    private final SelenideElement confirmText = $x("//div[contains(text(), 'Отправили СМС код на ваш номер телефона')]");

    @Override
    public TwoFactorAuthPage isPageOpened() {
        confirmText.shouldBe(visible);
        btnLogininOtp.shouldBe(visible);
        return this;
    }

    public TwoFactorAuthPage enterOtpCode(String code) {
        otpCodeField.setValue(code);
        return this;
    }

    public MainPage clickLoginOtpButton() {
        btnLogininOtp.click();
        return page(MainPage.class).isPageOpened();
    }

    public MainPage completeAuth(String code) {
        return enterOtpCode(code).clickLoginOtpButton();
    }
}
