package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Properties;
import static config.ApplicationProperties.ApplicationProperty.APP_ENV;

public class ApplicationProperties {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);
    private static HashMap<String, Properties> DEFAULT_VALUES = new HashMap<String, Properties>() {
        {
            put("default", new Properties() {
                {
                    //timeout and wait time properties
                    setProperty(ApplicationProperty.WAIT_TIMEOUT_SHT.value, "20");
                    setProperty(ApplicationProperty.WAIT_TIMEOUT.value, "15");
                    setProperty(ApplicationProperty.WAIT_TIMEOUT_LNG.value, "30");

                    setProperty(ApplicationProperty.TARGET_BROWSER.value, "FIREFOX"); // CHROME, FIREFOX

                    //application URL's
                    setProperty(ApplicationProperty.DOWNLOAD_FOLDER.value, "");

                    setProperty(ApplicationProperty.APP_URL.value, "https://172.18.0.4:7443");
                   // setProperty(ApplicationProperty.APP_URL.value, "https://localhost:7443");


                    //Selenium grid settings
                    setProperty(ApplicationProperty.REMOTE_DRIVER.value, "true");
                    setProperty(ApplicationProperty.SELENIUM_GRID_URL.value, "http://localhost:4444");

                    setProperty(ApplicationProperty.DESIRED_BROWSER_VERSION.value, "");
                    setProperty(ApplicationProperty.DESIRED_PLATFORM.value, "");
                    setProperty(ApplicationProperty.ENABLE_VIDEO.value, "true");

                    //Proxy settings
                    setProperty(ApplicationProperty.BROWSER_PROXY_ENABLED.value, "false");
                    setProperty(ApplicationProperty.PROXY_HOST.value, "10.23.2.250");
                    setProperty(ApplicationProperty.PROXY_PORT.value, "8080");

                    setProperty(ApplicationProperty.USER_NAME.value, "");
                    setProperty(ApplicationProperty.USER_PASSWORD.value, "");
                    setProperty(ApplicationProperty.HOST_MACHINE_PASSWORD.value, "");
                    setProperty(ApplicationProperty.WINDOWS_MACHINE_HOSTNAME.value, "");
                    setProperty(ApplicationProperty.WINDOWS_MACHINE_PASSWORD.value, "");
                    setProperty(ApplicationProperty.WINDOWS_MACHINE_IP.value, "");
                    setProperty(ApplicationProperty.WINDOWS_MACHINE_DOWNLOADS_FOLDER.value, "");
                    setProperty(ApplicationProperty.PATH_TO_COVENANT_DATA.value, "");


                }
            });
            put("other-dev", new Properties() {
                {
                }
            });
            put("local", new Properties() {
                {
                    setProperty(ApplicationProperty.APP_URL.value, "https://localhost:7443/home/index");
                }
            });

        }

    };

    private static String getString(String propertyName) {
        String currentEnv = System.getProperties().getProperty(
                APP_ENV.value,
                System.getenv(APP_ENV.value.toUpperCase().replace('.', '_')));

        if (System.getProperties().containsKey(propertyName)) {
            return System.getProperties().getProperty(propertyName);
        }
        if (currentEnv != null) {
            if (DEFAULT_VALUES.get(currentEnv).containsKey(propertyName)) {
                return DEFAULT_VALUES.get(currentEnv).getProperty(propertyName);
            }
        }
        if (DEFAULT_VALUES.get("default").containsKey(propertyName)) {
            return DEFAULT_VALUES.get("default").getProperty(propertyName);
        }

        logger.warn("Unknown application property: " + propertyName);
        throw new RuntimeException("Unknown application property: " + propertyName);
    }

    public static String getString(ApplicationProperty property) {
        return getString(property.value);
    }

    public static Integer getInteger(ApplicationProperty property) {
        return Integer.valueOf(getString(property));
    }


    public static boolean getBoolean(ApplicationProperty property) {
        return Boolean.valueOf(getString(property));
    }

    public enum ApplicationProperty {

        APP_ENV("application.env"),
        APP_URL("application.appUrl"),
        DOWNLOAD_FOLDER("download.folder"),
        TARGET_BROWSER("application.targetBrowser"),
        WAIT_TIMEOUT_SHT("application.timeoutShort"),
        WAIT_TIMEOUT("application.timeoutRegular"),
        WAIT_TIMEOUT_LNG("application.timeoutLong"),
        ENABLE_VIDEO("enableVideo"),
        SELENIUM_GRID_VIDEO_CAPTURE_URL("selenium.videoUrl"),
        PROXY_HOST("proxy.proxyHost"), PROXY_PORT("proxy.proxyPort"),
        REMOTE_DRIVER("selenium.remoteDriver"),
        DESIRED_BROWSER_VERSION("selenium.desiredBrowserVersion"),
        DESIRED_PLATFORM("selenium.desiredPlatform"),
        BROWSER_PROXY_ENABLED("proxy.browserProxyEnabled"),
        SELENIUM_GRID_URL("selenium.seleniumGridURL"),
        USER_NAME("user.nme"),
        USER_PASSWORD("user.password"),
        WINDOWS_MACHINE_HOSTNAME("vm.hostname"),
        WINDOWS_MACHINE_PASSWORD("vm.password"),
        WINDOWS_MACHINE_IP("vm.ip"),
        WINDOWS_MACHINE_DOWNLOADS_FOLDER("vm.downloads"),
        HOST_MACHINE_PASSWORD("hm.password"),
        PATH_TO_COVENANT_DATA("covenant.data");


        private String value;

        ApplicationProperty(String value) {
            this.value = value;
        }
    }
}