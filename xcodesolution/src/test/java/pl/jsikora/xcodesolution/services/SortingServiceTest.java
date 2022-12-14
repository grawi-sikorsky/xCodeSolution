package pl.jsikora.xcodesolution.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.jsikora.xcodesolution.dto.RequestNumbersDTO;
import pl.jsikora.xcodesolution.exceptions.BadOrderException;

@ExtendWith(MockitoExtension.class)
public class SortingServiceTest {
    
    private final SortingService sortingService = new SortingService();
    private static final List<Integer> testList = new ArrayList<Integer>(Arrays.asList(1,2,3,0));
    private static final RequestNumbersDTO testRequest = new RequestNumbersDTO(testList);

    @Test
    void sortNumbers_should_sort_numbers_asc() {
        testRequest.setOrder("ASC");

        assertEquals(sortingService.sortNumbers(testRequest).getNumbers(), Arrays.asList(0,1,2,3));
    }

    @Test
    void sortNumbers_should_sort_numbers_desc() {
        testRequest.setOrder("DESC");

        assertEquals(sortingService.sortNumbers(testRequest).getNumbers(), Arrays.asList(3,2,1,0));
    }

    @Test
    void sortNumbers_should_throw_exception_on_wrong_ordertype() {
        testRequest.setOrder("xxx");
        assertThrows(BadOrderException.class, () -> { sortingService.sortNumbers(testRequest); } );
    }

    @Test
    void sortNumbers_should_return_null_when_no_numbers() {
        final RequestNumbersDTO request = new RequestNumbersDTO();
        request.setOrder("ASC");

        assertEquals(null, sortingService.sortNumbers(request).getNumbers());
    }

    @Test
    void sortNumbers_should_return_empty_array_on_empty_Numberslist() {
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList());
        final RequestNumbersDTO request = new RequestNumbersDTO(list);
        request.setOrder("ASC");

        assertEquals(Arrays.asList(), sortingService.sortNumbers(request).getNumbers());
    }

}
