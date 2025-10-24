package racingcar.controller;

import java.util.List;
import java.util.regex.Pattern;
import racingcar.service.RacingGameInputValidationService;
import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class RacingGameController {
    private final RacingCarInputView racingCarInputView;
    private final RacingCarOutputView racingCarOutputView;
    private final RacingGameService racingGameService;
    private final RacingGameInputValidationService racingGameInputValidationService;

    private static final String carNamedelimiter = ",";

    public RacingGameController(RacingCarInputView racingCarInputView, RacingCarOutputView racingCarOutputView,
        RacingGameService racingGameService, RacingGameInputValidationService racingGameInputValidationService) {
        this.racingCarInputView = racingCarInputView;
        this.racingCarOutputView = racingCarOutputView;
        this.racingGameService = racingGameService;
        this.racingGameInputValidationService = racingGameInputValidationService;
    }

    public void racingGameStart() {
        List<String> carNames = getCarNames();
        racingGameInputValidationService.validateCarNames(carNames);

        String racingRoundAsString = getRacingRound();
        Integer racingRound = racingGameInputValidationService.convertStringToIntegerAndValidateRound(racingRoundAsString);
        
    }

    private String getRacingRound() {
        return racingCarInputView.inputRacingRound();
    }

    private List<String> getCarNames() {
        String carNameAsString = racingCarInputView.inputCarName();
        return List.of(carNameAsString.replace(" ", "").split(carNamedelimiter, -1));
    }

}
