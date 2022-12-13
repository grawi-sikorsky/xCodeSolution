package pl.jsikora.xcodesolution.dto;

import java.util.List;

public class RequestNumbersDTO {
    
    private List<Integer> numbers;
    private String order;

    public List<Integer> getNumbers() {
        return numbers;
    }
    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
}
