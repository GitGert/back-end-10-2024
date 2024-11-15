package ee.gert.veebipood.controller;

import ee.gert.veebipood.model.ParcelMachine;
import ee.gert.veebipood.model.SupplierProduct;
import ee.gert.veebipood.model.SupplierProductEscuela;
import ee.gert.veebipood.service.ParcelMachineService;
import ee.gert.veebipood.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    ParcelMachineService parcelMachineService;

//    @Autowired
//    SupplierServiceEscuela supplierServiceEscuela;


    @GetMapping("supplier")
    public List<SupplierProduct> getSupplierProducts(){
        return supplierService.getProducts();
    }

    @GetMapping("parcel-machines")
    public List<ParcelMachine> getParcelMachines(){
        return parcelMachineService.getParcelMachines();
    }

    @GetMapping("parcel-machines/{country}")
    public List<ParcelMachine> getParcelMachinesByCountry(@PathVariable String country){

        return parcelMachineService.getParcelMachinesByCountry(country);
    }


//    https://api.escuelajs.co/api/v1/products
@GetMapping("supplier-escuela")
public List<SupplierProductEscuela> getSupplierEscuela(){

    return supplierService.getProductsEscuela();
}
}
