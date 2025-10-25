package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;

public class RacingGameStartResponseDto {
    private List<Car> cars;
    private List<String> winners;
    private Integer racingRound;

    public RacingGameStartResponseDto() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }

    public Integer getRacingRound() {
        return racingRound;
    }

    public void setRacingRound(Integer racingRound) {
        this.racingRound = racingRound;
    }
}
