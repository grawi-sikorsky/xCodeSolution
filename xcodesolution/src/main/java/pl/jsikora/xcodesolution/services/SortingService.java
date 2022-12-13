package pl.jsikora.xcodesolution.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.jsikora.xcodesolution.dto.ResponseNumbersDTO;
import pl.jsikora.xcodesolution.dto.RequestNumbersDTO;
import pl.jsikora.xcodesolution.exceptions.BadOrderException;

@Service
public class SortingService {
    
    public ResponseNumbersDTO sortNumbers(RequestNumbersDTO requestNumbers){

        List<Integer> tempNumbers = requestNumbers.getNumbers();
        
        if(tempNumbers != null){
            if(requestNumbers.getOrder().equals("ASC")) tempNumbers.sort(Comparator.naturalOrder());
            else if (requestNumbers.getOrder().equals("DESC")) tempNumbers.sort(Comparator.reverseOrder());
            else throw new BadOrderException("Bad order type, only 'ASC' or 'DESC' allowed.");
        } 
        return new ResponseNumbersDTO(tempNumbers);
    }
}
