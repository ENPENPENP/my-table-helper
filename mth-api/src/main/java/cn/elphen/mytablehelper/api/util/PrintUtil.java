package cn.elphen.mytablehelper.api.util;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Elphen
 * @since 0.0.1
 */
public class  PrintUtil {

    public static void printMsg(String message) {
        System.out.print(message);
    }

    public static void printMsgLn(String message) {
        System.out.println(message);
    }

    public static void printException(Throwable throwable) {
        System.out.println("Reported exception:");
        throwable.printStackTrace();
    }

    public static void printException(String message, Throwable throwable) {
        printMsgLn(message);
        printException(throwable);
    }

    public static void printMsg(PrintWriter printWriter, String message) {
        if (printWriter != null && message != null) {
            printWriter.print(message);
        }
    }

    public static void printMsgLn(PrintWriter printWriter, String message) {
        if (printWriter != null && message != null) {
            printWriter.println(message);
        }
    }

    public static void printException(PrintWriter printWriter,Throwable throwable) {
        if (printWriter != null&& throwable != null) {
            printWriter.println("Reported exception:");
            printWriter.println(Arrays.toString(throwable.getStackTrace()));
        }
    }

    public static void printMsgException(PrintWriter printWriter, String message, Throwable throwable) {
        if (printWriter != null && message != null && throwable != null) {
            printMsgLn(printWriter, message);
            printException(printWriter, throwable);
        }
    }

}
