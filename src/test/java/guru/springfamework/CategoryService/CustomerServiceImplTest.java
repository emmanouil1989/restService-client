package guru.springfamework.CategoryService;

import guru.springfamework.EntitiesAbstract;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest extends EntitiesAbstract {

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

        assertEquals(FRISTNAME,customer.getFirstname());
        assertEquals(LASTNAME, customer.getLastname());
    }


    @Test
    public void createCustomer() {

        when(customerRepository.save(any())).thenReturn(getCustomer());


        CustomerDTO customer = customerService.createCustomer(getCustomer1DTO());

        assertNotNull(customer);
        assertEquals(OUAOU,customer.getFirstname());
    }

    @Test
    public void updateCustomer() {

        when(customerRepository.save(any())).thenReturn(getCustomer2());

        CustomerDTO response = customerService.updateCustomer(2l, getCustomer1DTO());

        assertNotNull(response);
        assertEquals(LASTNAME,response.getLastname());
    }
}