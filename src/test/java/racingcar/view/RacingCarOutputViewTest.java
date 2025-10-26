package racingcar.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

class RacingCarOutputViewTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private RacingCarOutputView racingCarOutputView;

    @BeforeEach
    void beforeEach() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        racingCarOutputView = new RacingCarOutputView();
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("받은 메시지 그대로 출력하는 테스트")
    void printMessageTest(){
        //given
        String message = "실행 결과";
        //when
        racingCarOutputView.printMessage(message);
        //then
        assertThat(outputStream.toString()).contains(message);
    }

    @Test
    @DisplayName("차량 전진 현황 출력 하는 테스트")
    void printProgressTest() {
        //given
        List<Car> cars = List.of(new Car("a1"), new Car("a2"), new Car("a3"));
        final Integer MOVABLE = 9;
        for(int i=1; i<=cars.size(); i++){
            cars.get(i-1).move(i,MOVABLE);
        }
        //when
        racingCarOutputView.printRacingProgress(cars);
        //then
        List<String> expectedPrints = List.of("a1 : -", "a2 : --" , "a3 : ---");
        assertThat(outputStream.toString()).contains(expectedPrints);
    }

    @Test
    @DisplayName("최종 우승자 출력 하는 테스트")
    void printFinalWinnersTest() {
        //given
        List<String> winners = List.of("a1","a2");
        //when
        racingCarOutputView.printFinalWinners(winners);
        //then
        String expectedPrints = "최종 우승자 : a1, a2";
        assertThat(outputStream.toString()).contains(expectedPrints);
    }
}