package ui.components.models;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.MainPage.*;

public class MainModel {

    public ListenerModel navigateToListenersPage() {
        retryingClick(LNK_LISTENERS.get());
        waitForVisibility(LBL_HEADER.get("Listeners"));
        return new ListenerModel();
    }

    public LauncherModel navigateToLaunchersPage() {
        click(LNK_LAUNCHERS.get());
        waitForVisibility(LBL_HEADER.get("Launchers"));
        return new LauncherModel();
    }

    public GruntModel navigateToGruntsPage() {
        click(LNK_GRUNTS.get());
        waitForVisibility(LBL_HEADER.get("Grunts"));
        return new GruntModel();
    }



}
