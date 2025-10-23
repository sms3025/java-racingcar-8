package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class Application {
    public static void main(String[] args) {
        RacingGameController racingGameController = getRacingGameController();
        racingGameController.racingGameStart();
    }

    private static RacingGameController getRacingGameController() {
        RacingCarInputView racingCarInputView = new RacingCarInputView();
        RacingCarOutputView racingCarOutputView = new RacingCarOutputView();
        RacingGameService racingGameService = new RacingGameService();
        return new RacingGameController(racingCarInputView,racingCarOutputView,racingGameService);
    }
}
