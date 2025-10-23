package racingcar.controller;

import java.util.List;
import java.util.regex.Pattern;
import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class RacingGameController {
    private final RacingCarInputView racingCarInputView;
    private final RacingCarOutputView racingCarOutputView;
    private final RacingGameService racingGameService;

    private static final String carNamedelimiter = ",";
    private static final Integer MAX_LENGTH_OF_CAR_NAME = 5;
    private static final Pattern CAR_NAME_REGEX = Pattern.compile("^[a-zA-Z0-9가-힣]+$");

    public RacingGameController(RacingCarInputView racingCarInputView, RacingCarOutputView racingCarOutputView,
        RacingGameService racingGameService) {
        this.racingCarInputView = racingCarInputView;
        this.racingCarOutputView = racingCarOutputView;
        this.racingGameService = racingGameService;
    }

    public void racingGameStart() {
        List<String> carNames = getCarNames();
        validateCarNames(carNames);

        String racingRoundAsString = getRacingRound();
        Integer racingRound = convertStringToIntegerAndValidateRound(racingRoundAsString);
    }

    private String getRacingRound() {
        return racingCarInputView.inputRacingRound();
    }

    private Integer convertStringToIntegerAndValidateRound(String racingRoundAsString) {
        Integer racingRound = convertStringToInteger(racingRoundAsString);
        validateZeroOrNegativeNumber(racingRound);
        return racingRound;
    }

    private void validateZeroOrNegativeNumber(Integer racingRound) {
        if(isZeroOrNegativeNumber(racingRound)) {
            throw new IllegalArgumentException("최소 1라운드 이상의 경기 수가 필요합니다.");
        }
    }

    private boolean isZeroOrNegativeNumber(Integer racingRound) {
        return racingRound <= 0;
    }

    private Integer convertStringToInteger(String racingRoundAsString) {
        try {
            return Integer.parseInt(racingRoundAsString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바르지 않은 경기 횟수 입니다.");
        }
    }

    private void validateCarNames(List<String> carNames) {
        carNames.forEach(carName -> {
            validateNullOrBlank(carName);
            validateCharacterSet(carName);
            validateMaximumLength(carName);
        });

        validateDuplicateCarName(carNames);
    }

    private void validateCharacterSet(String carName) {
        if(isInValidCarName(carName)) {
            throw new IllegalArgumentException("자동차 이름에는 한글, 영문, 숫자만 사용할 수 있습니다.");
        }
    }

    private boolean isInValidCarName(String carName) {
        return !CAR_NAME_REGEX.matcher(carName).matches();
    }

    private void validateDuplicateCarName(List<String> carNames) {
        if(isDuplicatedCarName(carNames)) {
            throw new IllegalArgumentException("중복된 자동차 이름이 있습니다.");
        }
    }

    private void validateMaximumLength(String carName) {
        if(isExceededMaximumLength(carName)) {
            throw new IllegalArgumentException("자동차 이름은 " + MAX_LENGTH_OF_CAR_NAME + "자 보다 클 수 없습니다.");
        }
    }

    private void validateNullOrBlank(String carName) {
        if(isNullOrBlank(carName)) {
            throw new IllegalArgumentException("올바르지 않은 이름입니다.");
        }
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

    private List<String> getCarNames() {
        String carNameAsString = racingCarInputView.inputCarName();
        return List.of(carNameAsString.replace(" ", "").split(carNamedelimiter, -1));
    }
}
