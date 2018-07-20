package guru.springfamework.api.v1.model.mapper;

import guru.springfamework.EntitiesAbstract;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest extends EntitiesAbstract {
    private static final long ID = 1L;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTOtest() {
        Customer customer = Customer.builder()
                .id(ID)
                .firstname(NAME)
                .lastname(NAME)
                .build();

        CustomerDTO customerDTO = customerMapper.convertToCustomerDTO(customer);

        assertEquals(NAME, customerDTO.getFirstname());
        assertEquals(NAME, customerDTO.getLastname());
    }

    @Test
    public void customerDTOtoCustomerTest() {
        Customer customer = customerMapper.convertToCustomer(getCustomer1DTO());

        assertEquals(NAME,customer.getFirstname());
        assertEquals(NAME,customer.getLastname());

    }
}