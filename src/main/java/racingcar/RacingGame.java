package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.dto.RacingGameSetupDto;

public class RacingGame {
    private final RacingGameController racingGameController;

    public RacingGame(RacingGameController racingGameController) {
        this.racingGameController = racingGameController;
    }

    public void startGame() {
        RacingGameSetupDto racingGameSetupDto = racingGameController.racingGameSetup();
        racingGameController.racingGameStart(racingGameSetupDto);
    }
}
