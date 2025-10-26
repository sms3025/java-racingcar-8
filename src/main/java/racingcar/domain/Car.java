package racingcar.domain;

import racingcar.error.ErrorCode;

public class Car implements Comparable<Car> {
    private String carName;
    private Integer position = 0;
    private static final Integer STANDARD_NUMBER = 4;

    public Car(String carName) {
        this.carName = carName;
    }

    public String getCarName() {
        return carName;
    }

    public Integer getPosition() {
        return position;
    }

    public void move(Integer distance, Integer randomNumber) {
        if (isGreaterOrEqualsStandardNumber(randomNumber)) {
            position = validateNextPositionAndReturn(distance);
        }
    }

    private boolean isGreaterOrEqualsStandardNumber(Integer randomNumber) {
        return randomNumber >= STANDARD_NUMBER;
    }

    private Integer validateNextPositionAndReturn(Integer distance) {
        Integer nextPosition = position + distance;

        if (validateIntegerOverFlow(distance, nextPosition)) {
            throw new ArithmeticException(ErrorCode.INTEGER_OVERFLOW.getErrorMessage());
        }
        return nextPosition;
    }

    private boolean validateIntegerOverFlow(Integer distance, Integer nextPosition) {
        return ((position ^ nextPosition) & (distance ^ nextPosition)) < 0;
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(this.getPosition(), other.getPosition());
    }
}
