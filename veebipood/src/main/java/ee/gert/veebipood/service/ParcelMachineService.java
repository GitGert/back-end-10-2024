package ee.gert.veebipood.service;

import ee.gert.veebipood.model.ParcelMachine;
import ee.gert.veebipood.model.SupplierProduct;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ParcelMachineService {

    public List<ParcelMachine> getParcelMachines(){

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://www.omniva.ee/locations.json";

//                                                    <---requestEntity is body + headers
        ResponseEntity<ParcelMachine[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ParcelMachine[].class );

        if (response.getBody() == null){
            return new ArrayList<>();
        }

        return  Arrays.asList(response.getBody());
    }


    public List<ParcelMachine> getParcelMachinesByCountry(String country) {
        String pmCountry = country.toUpperCase();
        List<ParcelMachine> parcelMachines = getParcelMachines();
//

//
//        for (ParcelMachine pm : parcelMachines){
//          if (pm.a0_NAME.equals(country)){
//              filteredMachineList.add(pm);
//          }
//        }
//


//        List<ParcelMachine> filteredMachineList = parcelMachines
//                .stream()
//                .filter(parcelMachine -> parcelMachine.a0_NAME.equals(country))
//                .toList();
//
//        return  filteredMachineList;

        return parcelMachines
                .stream()
                .filter(parcelMachine -> parcelMachine.a0_NAME.equals(pmCountry))
                .toList();
    }
}
