package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class RacingCarOutputView {
    private final String PROGRESS_OUTPUT_CHARACTER = "-";

    public RacingCarOutputView() {
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printRacingProgress(List<Car> cars) {
        cars.forEach(car -> {
            String positionVisual = PROGRESS_OUTPUT_CHARACTER.repeat(car.getPosition());
            System.out.println(car.getCarName() + " : " + positionVisual);
        });
        System.out.println();
    }

    public void printFinalWinners(List<String> winners) {
        String resultWinners = String.join(", ", winners);
        System.out.println("최종 우승자 : " + resultWinners);
    }
}
