package utils;

import config.ApplicationProperties;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


import static config.ApplicationProperties.ApplicationProperty.*;
import static config.ApplicationProperties.getString;

public class Utils {

    public static String generateRandomString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString.toLowerCase();
    }

    public static void copyFileToLocalMachine(String filename) throws IOException {
        String containerId = findBrowserContainerId(getString(TARGET_BROWSER));
        removeFileIfExists(filename);
        Runtime.getRuntime().exec(String.format("docker cp %s:/home/selenium/Downloads/%s %s/",
                containerId, filename, getString(DOWNLOAD_FOLDER)));
    }

    public static void uploadFileToWindowsMachine(String launcherName) throws IOException {
        Runtime.getRuntime().exec(String.format(
                "sshpass -p \"%s\" scp %s/%s %s@%s:%s/",
                getString(WINDOWS_MACHINE_PASSWORD), getString(DOWNLOAD_FOLDER), launcherName,
                getString(WINDOWS_MACHINE_HOSTNAME), getString(WINDOWS_MACHINE_IP), getString(WINDOWS_MACHINE_DOWNLOADS_FOLDER)));
    }

    public static void executeLauncher(String launcherName) throws IOException, InterruptedException {
        Runtime.getRuntime().exec(String.format("sshpass -p \"%s\" ssh %s@%s",
                getString(WINDOWS_MACHINE_PASSWORD), getString(WINDOWS_MACHINE_HOSTNAME), getString(WINDOWS_MACHINE_IP)));
        Thread.sleep(1000);
        Runtime.getRuntime().exec(String.format("%s\\%s",
                getString(WINDOWS_MACHINE_DOWNLOADS_FOLDER), launcherName));
        Runtime.getRuntime().exec("exit");
        Runtime.getRuntime().exec("logout");


    }

    public static String findBrowserContainerId(String browser) throws IOException {
        Process p = Runtime.getRuntime().exec("docker ps");

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        String s;
        while ((s = stdInput.readLine()) != null) {
            if (s.contains(browser.toLowerCase()))
                return s.split("   ")[0];
        }
        return null;
    }

    public static void removeFileIfExists(String filename) {
        if (new File(ApplicationProperties.getString(DOWNLOAD_FOLDER) + File.separator + filename).exists()) {
            new File(ApplicationProperties.getString(DOWNLOAD_FOLDER) + File.separator + filename).delete();
        }
    }

}
