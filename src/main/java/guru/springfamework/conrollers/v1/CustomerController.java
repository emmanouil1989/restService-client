package guru.springfamework.conrollers.v1;

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

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){

        CustomerDTO dto = customerService.updateCustomer(id, customerDTO);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){

        CustomerDTO dto = customerService.patchCustomer(id, customerDTO);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }



}
