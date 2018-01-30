package com.redstoner.nms;

import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;

public class NmsFactory
{
    private static final String NMS_DRIVER_CLASS = "com.redstoner.nms.%s.NmsDriver";
    private static final INmsDriver driver;
    private static final String nmsPackage;

    private static void logError(String description, Throwable error) {
        Bukkit.getLogger().severe("[Nms API] " + description);
        error.printStackTrace();
    }

    static {
        /*
        Static initialization should never throw an error
        This would cause the error to propagate to the code that first uses the driver,
        causing arbitrary parts of the server code to fail and other's to get null.
        If an exception occurs during initialization, this is instead printed to the console.
         */
        // get the nms package from the server
        String[] split = Bukkit.getServer().getClass().getPackage().getName().split("\\.");
        nmsPackage = split[split.length - 1];

        // instantiate the driver
        INmsDriver localDriver;
        try {
            String driverClassName = String.format(NMS_DRIVER_CLASS, nmsPackage);
            Class<?> driverClass = Class.forName(driverClassName);
            // get no-arg constructor explicitly, because direct newInstance() requires the driver to be public
            Constructor<?> constructor = driverClass.getConstructor();
            constructor.setAccessible(true);
            localDriver = (INmsDriver) constructor.newInstance();
        } catch (Exception ex) {
            // could use a mock implementation here to avoid NPE
            localDriver = null;
            logError("reflectively instantiating the Nms Driver for version " + nmsPackage, ex);
        }

        driver = localDriver;
    }

    public static INmsDriver getDriver() {
        return driver;
    }

    public static String getNmsPackage() {
        return nmsPackage;
    }

}
