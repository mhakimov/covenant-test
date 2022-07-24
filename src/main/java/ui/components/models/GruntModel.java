package ui.components.models;

import org.junit.jupiter.api.Assertions;

import static config.ApplicationProperties.ApplicationProperty.WINDOWS_MACHINE_HOSTNAME;
import static config.ApplicationProperties.getString;
import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.GruntsPage.LBL_GRUNT_NAMES;
import static ui.components.locators.Locators.GruntsPage.LBL_GRUNT_RECORD;

public class GruntModel extends MainModel {

    public GruntModel verifyConnectionBetweenGruntAndCovenant() {
        verifyGruntExists();
        return this;
    }

    public void verifyGruntExists() {
        boolean gruntExists = false;
        for(int i = 1; i <= waitForElements(LBL_GRUNT_NAMES.get()).size(); i++) {
            if(getText(LBL_GRUNT_RECORD.get(String.valueOf(i), "2")).equals(getString(WINDOWS_MACHINE_HOSTNAME))){
                gruntExists = true;
            }
        }
        Assertions.assertTrue(gruntExists);
    }


}
