package racingcar.controller;

import java.util.List;
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
        
    }

    private String getRacingRound() {
        return racingCarInputView.inputRacingRound();
    }

    private String getCarNames() {
        return racingCarInputView.inputCarName();
    }

}
