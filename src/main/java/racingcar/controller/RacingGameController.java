package racingcar.controller;

import java.util.List;
import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class RacingGameController {
    private final RacingCarInputView racingCarInputView;
    private final RacingCarOutputView racingCarOutputView;
    private final RacingGameService racingGameService;

    private final String carNamedelimiter = ",";

    public RacingGameController(RacingCarInputView racingCarInputView, RacingCarOutputView racingCarOutputView,
        RacingGameService racingGameService) {
        this.racingCarInputView = racingCarInputView;
        this.racingCarOutputView = racingCarOutputView;
        this.racingGameService = racingGameService;
    }

    public void racingGameStart() {
        String carNameAsString = racingCarInputView.inputCarName();
        List<String> carNames = List.of(carNameAsString.replace(" ", "").split(carNamedelimiter, -1));

        String racingRoundAsString = racingCarInputView.inputRacingRound();
        Integer racingRound = Integer.parseInt(racingRoundAsString);
    }
}
