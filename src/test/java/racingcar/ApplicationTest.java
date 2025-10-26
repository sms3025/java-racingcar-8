package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("2번 반복하는 테스트")
    void twoRepeatTest() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "2");
                    String output = output();
                    assertThat(findWordCount(output, "pobi")).isEqualTo(3);
                    assertThat(findWordCount(output, "woni")).isEqualTo(2);
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    private static Integer findWordCount(String output, String findWord) {
        Integer index = 0;
        Integer count = 0;
        while ((index = output.indexOf(findWord, index)) != -1) {
            index += findWord.length();
            count++;
        }
        return count;
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
