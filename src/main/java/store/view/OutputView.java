package store.view;

public class OutputView {
    public static void printError(String message, Exception e) {
        System.out.println("[ERROR] " + message + " - " + e.getMessage());
    }
}

