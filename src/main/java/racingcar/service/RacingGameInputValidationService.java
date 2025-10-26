package racingcar.service;

import java.util.List;
import java.util.regex.Pattern;
import racingcar.error.ErrorCode;

public class RacingGameInputValidationService {
    private static final String CAR_NAME_DELIMITER = ",";
    private static final Integer MAX_LENGTH_OF_CAR_NAME = 5;
    private static final Pattern CAR_NAME_REGEX = Pattern.compile("^[a-zA-Z0-9가-힣]+$");

    public RacingGameInputValidationService() {
    }

    public List<String> splitStringAndValidateCarNames(String carNameAsString) {
        List<String> carNames = getSplitCarNames(carNameAsString);
        carNames.forEach(carName -> {
            validateNullOrBlank(carName);
            validateCharacterSet(carName);
            validateMaximumLength(carName);
        });

        validateDuplicateCarName(carNames);
        return carNames;
    }

    public Integer convertStringToIntegerAndValidateRound(String racingRoundAsString) {
        Integer racingRound = convertStringToInteger(racingRoundAsString);
        validateZeroOrNegativeNumber(racingRound);
        return racingRound;
    }

    private List<String> getSplitCarNames(String carNameAsString) {
        validateNullOrBlank(carNameAsString);
        return List.of(carNameAsString.replace(" ", "").split(CAR_NAME_DELIMITER, -1));

    }

    private Integer convertStringToInteger(String racingRoundAsString) {
        try {
            return Integer.parseInt(racingRoundAsString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.ILLEGAL_RACING_ROUND.getErrorMessage());
        }
    }

    private void validateZeroOrNegativeNumber(Integer racingRound) {
        if (isZeroOrNegativeNumber(racingRound)) {
            throw new IllegalArgumentException(ErrorCode.NOT_POSITIVE_RACING_ROUND.getErrorMessage());
        }
    }

    private void validateCharacterSet(String carName) {
        if (isInValidCarName(carName)) {
            throw new IllegalArgumentException(ErrorCode.FORBIDDEN_CAR_NAME.getErrorMessage());
        }
    }

    private void validateDuplicateCarName(List<String> carNames) {
        if (isDuplicatedCarName(carNames)) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_CAR_NAME.getErrorMessage());
        }
    }

    private void validateNullOrBlank(String carName) {
        if (isNullOrBlank(carName)) {
            throw new IllegalArgumentException(ErrorCode.ILLEGAL_CAR_NAME.getErrorMessage());
        }
    }

    private void validateMaximumLength(String carName) {
        if (isExceededMaximumLength(carName)) {
            throw new IllegalArgumentException(ErrorCode.OVER_MAXIMUM_LENGTH_CAR_NAME.getErrorMessage());
        }
    }

    private boolean isZeroOrNegativeNumber(Integer racingRound) {
        return racingRound <= 0;
    }

    private boolean isInValidCarName(String carName) {
        return !CAR_NAME_REGEX.matcher(carName).matches();
    }

    private boolean isDuplicatedCarName(List<String> carNames) {
        return carNames.stream().distinct().count() != carNames.size();
    }

    private boolean isExceededMaximumLength(String carName) {
        return carName.length() > MAX_LENGTH_OF_CAR_NAME;
    }

    private boolean isNullOrBlank(String carName) {
        return carName == null || carName.isBlank();
    }
}
