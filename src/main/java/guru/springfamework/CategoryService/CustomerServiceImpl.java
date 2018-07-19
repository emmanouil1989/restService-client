package guru.springfamework.CategoryService;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.mapper.CustomerMapper;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

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
                .map(customer -> customerMapper.convertToCustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.convertToCustomerDTO(customerRepository.findById(id).get());
    }
}
