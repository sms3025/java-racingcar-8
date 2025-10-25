package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;

public class RacingGameSetupDto {
    private List<Car> cars;
    private Integer racingRound;

    public RacingGameSetupDto() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Integer getRacingRound() {
        return racingRound;
    }

    public void setRacingRound(Integer racingRound) {
        this.racingRound = racingRound;
    }
}
