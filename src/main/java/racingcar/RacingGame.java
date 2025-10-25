package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.dto.RacingGameSetupDto;
import racingcar.dto.RacingGameStartResponseDto;

public class RacingGame {
    private final RacingGameController racingGameController;

    public RacingGame(RacingGameController racingGameController) {
        this.racingGameController = racingGameController;
    }

    public void startGame() {
        RacingGameSetupDto racingGameSetupDto = racingGameController.racingGameSetup();
        RacingGameStartResponseDto racingGameStartResponseDto = racingGameController.racingGameStart(
            racingGameSetupDto);

    }
}
