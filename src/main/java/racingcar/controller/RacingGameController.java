package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.dto.RacingGameSetupDto;
import racingcar.dto.RacingGameStartResponseDto;
import racingcar.service.RacingGameInputValidationService;
import racingcar.service.RacingGameService;
import racingcar.view.RacingCarInputView;
import racingcar.view.RacingCarOutputView;

public class RacingGameController {
    private final RacingCarInputView racingCarInputView;
    private final RacingCarOutputView racingCarOutputView;
    private final RacingGameService racingGameService;
    private final RacingGameInputValidationService racingGameInputValidationService;
    private final String PROGRESS_RESULT = "실행 결과";

    public RacingGameController(RacingCarInputView racingCarInputView, RacingCarOutputView racingCarOutputView,
                                RacingGameService racingGameService,
                                RacingGameInputValidationService racingGameInputValidationService) {
        this.racingCarInputView = racingCarInputView;
        this.racingCarOutputView = racingCarOutputView;
        this.racingGameService = racingGameService;
        this.racingGameInputValidationService = racingGameInputValidationService;
    }

    public RacingGameSetupDto racingGameSetup() {
        String carNameAsString = getCarNames();
        List<String> carNames = racingGameInputValidationService.splitStringAndValidateCarNames(carNameAsString);

        String racingRoundAsString = getRacingRound();
        Integer racingRound = racingGameInputValidationService.convertStringToIntegerAndValidateRound(
                racingRoundAsString);

        List<Car> cars = carNames.stream().map(Car::new).toList();

        return getRacingGameSetupDto(cars, racingRound);
    }

    public RacingGameStartResponseDto racingGameStart(RacingGameSetupDto racingGameSetupDto) {
        List<Car> cars = racingGameSetupDto.getCars();
        Integer totalRacingRound = racingGameSetupDto.getRacingRound();
        Integer currentRacingRound = 0;

        racingCarOutputView.printMessage(PROGRESS_RESULT);
        do {
            cars = racingGameService.raceOneRound(cars);
            currentRacingRound = increaseCurrentRacingRound(currentRacingRound);
            racingCarOutputView.printRacingProgress(cars);
        } while (isPlayableRound(currentRacingRound, totalRacingRound));

        List<String> finalWinners = racingGameService.getFinalWinners(cars);
        racingCarOutputView.printFinalWinners(finalWinners);

        return getRacingGameStartResponseDto(currentRacingRound,
                finalWinners, cars);
    }

    private RacingGameStartResponseDto getRacingGameStartResponseDto(Integer currentRacingRound,
                                                                     List<String> finalWinners, List<Car> cars) {
        RacingGameStartResponseDto racingGameStartResponseDto = new RacingGameStartResponseDto();
        racingGameStartResponseDto.setRacingRound(currentRacingRound);
        racingGameStartResponseDto.setWinners(finalWinners);
        racingGameStartResponseDto.setCars(cars);
        return racingGameStartResponseDto;
    }

    private RacingGameSetupDto getRacingGameSetupDto(List<Car> cars, Integer racingRound) {
        RacingGameSetupDto racingGameSetupDto = new RacingGameSetupDto();
        racingGameSetupDto.setCars(cars);
        racingGameSetupDto.setRacingRound(racingRound);
        return getRacingGameSetupResponseDto(racingGameSetupDto);
    }

    private RacingGameSetupDto getRacingGameSetupResponseDto(
            RacingGameSetupDto racingGameSetupResponseDto) {
        return racingGameSetupResponseDto;
    }

    private boolean isPlayableRound(Integer currentRacingRound, Integer totalRacingRound) {
        return currentRacingRound < totalRacingRound;
    }

    private Integer increaseCurrentRacingRound(Integer currentRacingRound) {
        currentRacingRound++;
        return currentRacingRound;
    }

    private String getRacingRound() {
        return racingCarInputView.inputRacingRound();
    }

    private String getCarNames() {
        return racingCarInputView.inputCarName();
    }

}
