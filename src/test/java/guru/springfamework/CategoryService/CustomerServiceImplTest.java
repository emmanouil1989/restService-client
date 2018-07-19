package guru.springfamework.CategoryService;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.mapper.CustomerMapper;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    private static final String FRISTNAME = "manos";
    private static final String LASTNAME = "manos";
    @Mock
    CustomerRepository customerRepository;


    CustomerService customerService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository,CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> list = getCustomerList();
        when(customerRepository.findAll()).thenReturn(list);

        List<CustomerDTO> customers = customerService.getAllCustomers();

        assertEquals(2,customers.size());
    }


    @Test
    public void getCustomerById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(getCustomer2()));

        CustomerDTO customer = customerService.getCustomerById(1L);

        assertEquals(FRISTNAME,customer.getFristname());
        assertEquals(LASTNAME, customer.getLastname());
    }

    private List<Customer> getCustomerList() {
        return Arrays.asList(getCustomer(),getCustomer2());
    }

    private Customer getCustomer2() {
        return Customer.builder()
                .id(1L)
                .lastname(LASTNAME)
                .fristname(FRISTNAME)
                .build();
    }

    private Customer getCustomer() {
        String ouaou = "ouaou";
        return Customer.builder()
                .fristname(ouaou)
                .lastname(ouaou)
                .build();
    }

}