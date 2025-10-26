package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class RacingCarInputView {
    private static final String INPUT_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_RACING_ROUND_MESSAGE = "시도할 횟수는 몇 회인가요?";

    public RacingCarInputView() {
    }

    public String inputCarName() {
        RacingCarOutputView.printMessage(INPUT_CAR_NAME_MESSAGE);
        return Console.readLine();
    }

    public String inputRacingRound() {
        RacingCarOutputView.printMessage(INPUT_RACING_ROUND_MESSAGE);
        return Console.readLine();
    }
}
