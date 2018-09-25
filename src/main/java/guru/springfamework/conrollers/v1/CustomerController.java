package guru.springfamework.conrollers.v1;

import guru.springfamework.CategoryService.CustomerService;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiDescription;

@RestController
@Api(description = "This is my customer controller")
@RequestMapping("/api/v1/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ApiOperation(value = "This is get a list of customer", notes = "those are notes.")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
