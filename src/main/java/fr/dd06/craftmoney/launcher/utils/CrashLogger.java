package fr.dd06.craftmoney.launcher.utils;

import fr.dd06.craftmoney.launcher.CraftMoneyGame;

import java.io.*;

public class CrashLogger {

    public static File CRASH_DIR = new File(CraftMoneyGame.CRAFTMONEY_GAME_DIR, "/crashes/");

    public void createCrashLog(Exception e) {
        int crashNumber = 0;
        File crashFile = new File(CRASH_DIR, "crash-0.txt");
        while (crashFile.exists()) {
            crashNumber++;
            crashFile = new File(CRASH_DIR, "crash-" + crashNumber + ".txt");
        }

        crashFile.getParentFile().mkdirs();
        try {
            crashFile.createNewFile();
            String crashLog = "Exception: " + e.toString() + "\n";


            StackTraceElement[] stackTrace = e.getStackTrace();

            for (StackTraceElement element : stackTrace) {
                crashLog += "\n\r#     " + element;
            }
            Throwable cause = e.getCause();
            if (cause != null) {
                crashLog += "\n\r# Caused by: " + cause.toString();

                StackTraceElement[] causeStackTrace = cause.getStackTrace();

                for (StackTraceElement element : causeStackTrace) {

                    crashLog+= "\n\r#     " + element;

                }

            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(crashFile));
            writer.write(crashLog);
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
