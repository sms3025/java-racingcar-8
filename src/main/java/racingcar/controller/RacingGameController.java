package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.service.RacingGameInputValidationService;
import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class RacingGameController {
    private final RacingCarInputView racingCarInputView;
    private final RacingCarOutputView racingCarOutputView;
    private final RacingGameService racingGameService;
    private final RacingGameInputValidationService racingGameInputValidationService;

    public RacingGameController(RacingCarInputView racingCarInputView, RacingCarOutputView racingCarOutputView,
        RacingGameService racingGameService, RacingGameInputValidationService racingGameInputValidationService) {
        this.racingCarInputView = racingCarInputView;
        this.racingCarOutputView = racingCarOutputView;
        this.racingGameService = racingGameService;
        this.racingGameInputValidationService = racingGameInputValidationService;
    }

    public void racingGameStart() {
        String carNameAsString = getCarNames();
        List<String> carNames = racingGameInputValidationService.splitStringAndValidateCarNames(carNameAsString);

        String racingRoundAsString = getRacingRound();
        Integer racingRound = racingGameInputValidationService.convertStringToIntegerAndValidateRound(racingRoundAsString);

        List<Car> cars = carNames.stream().map(Car::new).toList();

        while(isPlayableRound(racingRound)) {
            cars = racingGameService.raceOneRound(cars);
            racingRound = decreaseRacingRound(racingRound);
        }
        List<String> finalWinners = racingGameService.getFinalWinners(cars);
    }

    private boolean isPlayableRound(Integer racingRound) {
        return racingRound > 0;
    }

    private Integer decreaseRacingRound(Integer racingRound) {
        racingRound--;
        return racingRound;
    }

    private String getRacingRound() {
        return racingCarInputView.inputRacingRound();
    }

    private String getCarNames() {
        return racingCarInputView.inputCarName();
    }

}
