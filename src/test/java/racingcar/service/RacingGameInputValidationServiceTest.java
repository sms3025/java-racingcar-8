package racingcar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.error.ErrorCode;

class RacingGameInputValidationServiceTest {

    private RacingGameInputValidationService validationService;

    @BeforeEach
    void beforeEach() {
        validationService = new RacingGameInputValidationService();
    }

    @Test
    @DisplayName("정상적인 차량 이름 테스트")
    void NormalCarNameParsingTest() {
        //given
        String carName = "pobi,  준1 ,KK123,개리,abcde";
        //when
        List<String> carNames = validationService.splitStringAndValidateCarNames(carName);
        //then
        assertThat(carNames).containsExactly("pobi", "준1", "KK123", "개리", "abcde");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", "\n", ",a", "a,,b", "a,b,"})
    @DisplayName("차량 이름이 null, 빈 문자열, 공백인 경우 예외 발생 테스트")
    void nullOrBlankOrIllegalCarNameTest(String carNames) {
        //when & then
        assertThatThrownBy(() -> validationService.splitStringAndValidateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.ILLEGAL_CAR_NAME.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab-c", "ab_c", "ab!c", "a.b", "a@b", "@@@"})
    @DisplayName("차량 이름이 한글, 영어, 숫자가 아닌 경우 예외 발생 테스트")
    void invalidCharsetTest(String carNames) {
        //when & then
            assertThatThrownBy(() -> validationService.splitStringAndValidateCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.FORBIDDEN_CAR_NAME.getErrorMessage());

    }

    @Test
    @DisplayName("5자 이상 길이의 차량 이름 예외 테스트")
    void overMaximumLengthCarNameTest() {
        //given
        String carNames = "abcdef,kummmmmmm,waidjaowdijwa";
        //when & then
        assertThatThrownBy(() -> validationService.splitStringAndValidateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.OVER_MAXIMUM_LENGTH_CAR_NAME.getErrorMessage());
    }

    @Test
    @DisplayName("중복된 이름을 가진 차량 이름 예외 테스트")
    void duplicatedCarNameTest() {
        //given
        String carNames = "pobi,jun,pobi";
        //when & then
        assertThatThrownBy(() -> validationService.splitStringAndValidateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.DUPLICATE_CAR_NAME.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "7", "42", "2147483647"})
    @DisplayName("일반적인 양수 값을 가진 경기 수 테스트")
    void normalRacingRoundTest(String racingRoundAsString) {
        //when
        Integer racingRound = validationService.convertStringToIntegerAndValidateRound(racingRoundAsString);
        //then
        assertThat(racingRound).isGreaterThan(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-10", "-2147483648"})
    @DisplayName("0 또는 음수 값을 가진 경기 수 테스트")
    void zeroOrNegativeRacingRoundTest(String racingRoundAsString) {
        //when & then
        assertThatThrownBy(() -> validationService.convertStringToIntegerAndValidateRound(racingRoundAsString))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.NOT_POSITIVE_RACING_ROUND.getErrorMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", " ", "   ", "1.5", "ab", "+", "-", "--1", "98b"})
    @DisplayName("정수가 아닌 경기 수 테스트")
    void notIntegerRacingRoundTest(String racingRoundAsString) {
        //when & then
        assertThatThrownBy(() -> validationService.convertStringToIntegerAndValidateRound(racingRoundAsString))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.ILLEGAL_RACING_ROUND.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2147483648", "9999999999", "9223372036854775807"})
    @DisplayName("오버 플로우를 발생시키는 경기 수 테스트")
    void overflowRacingRoundTest(String racingRoundAsString) {
        //when & then
        assertThatThrownBy(() -> validationService.convertStringToIntegerAndValidateRound(racingRoundAsString))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.ILLEGAL_RACING_ROUND.getErrorMessage());
    }

}