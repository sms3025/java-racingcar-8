package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingCarInputViewTest {

    private RacingCarInputView racingCarInputView = new RacingCarInputView();

    @AfterEach
    void afterEach() {
        Console.close();
    }

    @Test
    @DisplayName("자동차 이름 입력 확인 테스트")
    void carNameInputTest() {
        //given
        String expectedString = "pobi,woni,jun";
        System.setIn(new ByteArrayInputStream(expectedString.getBytes()));

        //when
        String result = racingCarInputView.inputCarName();

        //then
        Assertions.assertThat(result).isEqualTo(expectedString);
    }

    @Test
    @DisplayName("자동차 경기 시도 횟수 입력 확인 테스트")
    void RacingRoundInputTest() {
        //given
        String expectedString = "5";
        System.setIn(new ByteArrayInputStream(expectedString.getBytes()));

        //when
        String result = racingCarInputView.inputRacingRound();

        //then
        Assertions.assertThat(result).isEqualTo(expectedString);
    }
}