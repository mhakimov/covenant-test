package smoke;

import config.ApplicationProperties;
import config.annotations.Dataset;
import context.TestContext;
import org.junit.jupiter.api.Test;
import ui.components.models.MainModel;

import java.io.IOException;

import static config.ApplicationProperties.ApplicationProperty.USER_NAME;
import static config.ApplicationProperties.ApplicationProperty.USER_PASSWORD;
import static utils.Utils.*;

public class E2ETests extends TestContext {

    private final static String listenerPort = "8082";
    private final static String launcherName = "GruntSMB.exe";

    @Test
    @Dataset("Dataset")
    void verifyConnectionBetweenGruntAndCovenant() throws IOException, InterruptedException {
        String listenerName = generateRandomString(7);

        openApp()
                .login(ApplicationProperties.getString(USER_NAME), ApplicationProperties.getString(USER_PASSWORD))
                .navigateToListenersPage()
                .createNewListener(listenerName, listenerPort)
                .navigateToLaunchersPage()
                .generateLauncher("5")
                .downloadLauncher(launcherName);

        copyFileToLocalMachine(launcherName);
        uploadFileToWindowsMachine(launcherName);
        executeLauncher(launcherName);

        new MainModel().navigateToGruntsPage()
                .verifyConnectionBetweenGruntAndCovenant();
    }
}
