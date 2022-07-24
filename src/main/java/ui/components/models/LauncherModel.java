package ui.components.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.LaunchersPage.*;


public class LauncherModel extends MainModel {

    private static final Logger logger = LoggerFactory.getLogger(LauncherModel.class);

    public LauncherModel generateLauncher(String index) {
        logger.info("generating launcher");

        click(LNK_LAUNCHER.get(index));
        click(BTN_GENERATE.get());
        return this;
    }

    public LauncherModel downloadLauncher(String filename) {
        click(BTN_DOWNLOAD.get());
        return this;
    }





}
