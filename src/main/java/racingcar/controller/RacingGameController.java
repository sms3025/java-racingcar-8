package racingcar.controller;

import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class RacingGameController {
    private final RacingCarInputView racingCarInputView;
    private final RacingCarOutputView racingCarOutputView;
    private final RacingGameService racingGameService;

    public RacingGameController(RacingCarInputView racingCarInputView, RacingCarOutputView racingCarOutputView,
        RacingGameService racingGameService) {
        this.racingCarInputView = racingCarInputView;
        this.racingCarOutputView = racingCarOutputView;
        this.racingGameService = racingGameService;
    }

    public void racingGameStart() {
        String carNameAsString = racingCarInputView.inputCarName();
        String racingRoundAsString = racingCarInputView.inputRacingRound();
    }
}
