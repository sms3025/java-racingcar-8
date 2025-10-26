package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import racingcar.domain.Car;
import racingcar.error.ErrorCode;

public class RacingGameService {
    private static final Integer DISTANCE = 1;
    private static final Integer START_CONDITIONAL_NUMBER = 0;
    private static final Integer END_CONDITIONAL_NUMBER = 9;

    public RacingGameService() {
    }

    public List<Car> raceOneRound(List<Car> cars) {
        cars.forEach(car -> car.move(DISTANCE, getRandomNumber()));
        return cars;
    }

    public List<String> getFinalWinners(List<Car> cars) {
        Integer maxPosition = getMaxPosition(cars);

        return cars.stream()
                .filter(car -> car.getPosition().equals(maxPosition))
                .map(Car::getCarName)
                .toList();
    }

    private Integer getMaxPosition(List<Car> cars) {
        return cars.stream()
                .max(Car::compareTo)
                .map(Car::getPosition)
                .orElseThrow(() -> new IllegalStateException(ErrorCode.EMPTY_CAR_LIST.getErrorMessage()));
    }

    private int getRandomNumber() {
        return Randoms.pickNumberInRange(START_CONDITIONAL_NUMBER, END_CONDITIONAL_NUMBER);
    }
}
