package racingcar.error;

public enum ErrorCode {
    ILLEGAL_CAR_NAME("올바르지 않은 이름입니다."),
    FORBIDDEN_CAR_NAME("자동차 이름에는 한글, 영문, 숫자만 사용할 수 있습니다."),
    OVER_MAXIMUM_LENGTH_CAR_NAME("자동차 이름은 5자 보다 클 수 없습니다."),
    DUPLICATE_CAR_NAME("중복된 자동차 이름이 있습니다."),
    ILLEGAL_RACING_ROUND("올바르지 않은 경기 횟수 입니다."),
    NOT_POSITIVE_RACING_ROUND("최소 1라운드 이상의 경기 수가 필요합니다.");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
