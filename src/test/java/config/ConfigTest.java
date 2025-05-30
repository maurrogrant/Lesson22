package config;

import com.codeborne.selenide.Configuration;

import java.util.Map;

public class ConfigTest {
    public static void initConfig() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 60_000; // 60 секунд
        Configuration.timeout = 30_000; // 30 секунд для ожиданий элементов
        Configuration.browserCapabilities.setCapability("goog:chromeOptions",
                Map.of("prefs", Map.of("intl.accept_languages", "ru,ru_RU")));
        Configuration.downloadsFolder = "target/downloads";
    }
}
