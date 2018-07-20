package guru.springfamework.conrollers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.CategoryService.CustomerService;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomers(){
        return new ResponseEntity<>(CustomerListDTO.builder().customers(customerService.getAllCustomers()).build(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){

        CustomerDTO customer = customerService.createCustomer(customerDTO);
        return  new ResponseEntity<>(customer,HttpStatus.CREATED);
    }

}
