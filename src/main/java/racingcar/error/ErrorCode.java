package racingcar.error;

public enum ErrorCode {
    DUPLICATE_CAR_NAME("중복된 이름이 있습니다.");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
