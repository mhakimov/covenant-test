package ui.components.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.ListenersPage.*;
import static ui.components.locators.Locators.MainPage.*;

public class ListenerModel extends MainModel {

    private static final Logger logger = LoggerFactory.getLogger(ListenerModel.class);

    public ListenerModel createNewListener(String listenerName, String portNumber) {
        logger.info("creating new listener");

        retryingClick(BTN_CREATE_LISTENER.get());
        jsClick(LNK_BRIDGE_LISTENERS.get());
        selectByValue(DDM_BRIDGE_PROFILE.get(), "3");
        sendKeys(TXF_NAME.get(), listenerName);
        sendKeys(TXF_BIND_PORT.get(), portNumber);
        sendKeys(TXF_CONNECT_PORT.get(), portNumber);
        click(BTN_CREATE_BRIDGE_LISTENER.get());
        waitForElement(LBL_HEADER.get("Listeners"));
        return this;
    }
}
