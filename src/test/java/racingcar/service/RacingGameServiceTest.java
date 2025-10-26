package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

class RacingGameServiceTest {
    private RacingGameService racingGameService;

    @BeforeEach
    void beforeEach() {
        racingGameService = new RacingGameService();
    }

    @Test
    @DisplayName("한 명의 우승자가 나오는 테스트")
    void findOneWinnerTest() {
        //given
        List<Car> cars = List.of(new Car("a1"), new Car("a2"), new Car("a3"));
        String expectedWinners = "a3";
        Integer movedRandomNumber = 9;
        Integer distance = 1;
        for (int i = 0; i < cars.size(); i++) {
            for (int j = 0; j <= i; j++) {
                cars.get(i).move(distance, movedRandomNumber);
            }
        }
        //when
        List<String> winners = racingGameService.getFinalWinners(cars);
        //then
        assertThat(winners).containsExactly(expectedWinners);
    }

    @Test
    @DisplayName("여러명의 우승자가 나오는 테스트")
    void findManyWinnerTest() {
        //given
        List<Car> cars = List.of(new Car("a1"), new Car("a2"), new Car("a3"));
        List<String> expectedWinners = List.of("a1", "a2");
        Integer movedRandomNumber = 9;
        Integer distance = 1;
        for (int i = 0; i < cars.size() - 1; i++) {
            for (int j = 0; j <= cars.size() - 1; j++) {
                cars.get(i).move(distance, movedRandomNumber);
            }
        }
        //when
        List<String> winners = racingGameService.getFinalWinners(cars);
        //then
        assertThat(winners).containsExactlyInAnyOrderElementsOf(expectedWinners);
    }


}