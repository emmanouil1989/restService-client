package guru.springfamework.CategoryService;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.mapper.CustomerMapper;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String API_V2_CUSTOMER = "/api/v2/customer/";
    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.convertToCustomerDTO(customer);
                    customerDTO.setCustomer_url(API_V2_CUSTOMER + customer.getId().toString());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id).map(customer -> {
            CustomerDTO customerDTO = customerMapper.convertToCustomerDTO(customer);
            customerDTO.setCustomer_url(API_V2_CUSTOMER + customer.getId().toString());
            return customerDTO;
        })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.convertToCustomer(customerDTO);

        return saveCustomerAndReturnDTO(customer);
    }

    private CustomerDTO saveCustomerAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnedDto = customerMapper.convertToCustomerDTO(savedCustomer);

        returnedDto.setCustomer_url(API_V2_CUSTOMER + savedCustomer.getId());

        return returnedDto;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.convertToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomerAndReturnDTO(customer);
    }
}
