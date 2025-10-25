package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.error.ErrorCode;

class CarTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("기준 값 미만(0~3)일 때, 차량이 전진하지 않는 테스트")
    void NotMovedCarTest(int randomNumber) {
        // given
        Car car = new Car("pobi");
        Integer expectedPosition = 0;
        // when
        car.move(1, randomNumber);
        // then
        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    @DisplayName("기준 값 이상(4~9)일 때, 차량이 전진하는 테스트")
    void moveCarTest(int randomNumber) {
        // given
        Car car = new Car("pobi");
        Integer expectedPosition = 1;
        // when
        car.move(1, randomNumber);
        // then
        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("여러번 값이 들어올 때, 차량이 누적해서 전진하는 테스트")
    void accumulatedPositionTest() {
        // given
        Car car = new Car("pobi");
        List<Integer> randomNumbers = List.of(9, 3, 8, 7);
        Integer expectedPosition = 3;
        // when
        randomNumbers.forEach(randomNumber -> car.move(1, randomNumber));
        // then
        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("오버플로우 발생 시, 예외를 던지는 테스트")
    void overFlowTest() {
        // given
        Car car = new Car("pobi");
        Integer randomNumber = 9;
        car.move(Integer.MAX_VALUE, randomNumber);
        // when & then
        assertThatThrownBy(() -> car.move(1, randomNumber))
            .isInstanceOf(ArithmeticException.class)
            .hasMessage(ErrorCode.INTEGER_OVERFLOW.getErrorMessage());
    }
}