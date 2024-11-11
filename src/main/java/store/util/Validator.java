package store.util;

public class Validator {

    public static void validateInputFormat(String input) {
        if (input == null || input.isEmpty() || !input.matches("(\\[\\w+-\\d+],?)+")) {
            throw new IllegalArgumentException("[ERROR] 입력 형식이 올바르지 않습니다. 예시 형식을 확인하세요.");
        }
    }
}
