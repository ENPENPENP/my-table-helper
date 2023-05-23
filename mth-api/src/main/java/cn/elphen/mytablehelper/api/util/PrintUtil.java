package cn.elphen.mytablehelper.api.util;

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

}
