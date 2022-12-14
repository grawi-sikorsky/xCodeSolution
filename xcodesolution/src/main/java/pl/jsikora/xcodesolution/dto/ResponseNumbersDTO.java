package pl.jsikora.xcodesolution.dto;

import java.util.List;

public class ResponseNumbersDTO {
    private List<Integer> numbers;

    public ResponseNumbersDTO(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public ResponseNumbersDTO() {
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }


}
