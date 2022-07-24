package ui.components.models;

import config.ApplicationProperties;
//import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static config.ApplicationProperties.ApplicationProperty.APP_URL;
import static config.ApplicationProperties.ApplicationProperty.USER_NAME;
import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.LoginPage.*;
import static ui.components.locators.Locators.MainPage.*;

public class LoginModel {

    private static final Logger logger = LoggerFactory.getLogger(LoginModel.class);


    public DashboardModel login(String username, String password) {
        sendKeys(TXF_USERNAME.get(), username);
        sendKeys(TXF_PASSWORD.get(), password);
        if(isElementDisplayed(LBL_REGISTER_INITIAL_USER.get())) {
            sendKeys(TXF_PASSWORD_CONFIRM.get(), password);
        }
        click(BTN_LOGIN.get());
        waitForElement(LBL_HEADER.get("Dashboard"));
        return new DashboardModel();
    }



}
