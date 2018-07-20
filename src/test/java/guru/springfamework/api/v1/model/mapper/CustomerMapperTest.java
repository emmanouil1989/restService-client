package guru.springfamework.api.v1.model.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {
    private static final long ID = 1L;
    private static final String NAME = "Joe";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {
        Customer customer = Customer.builder()
                .id(ID)
                .fristname(NAME)
                .lastname(NAME)
                .build();

        CustomerDTO customerDTO = customerMapper.convertToCustomerDTO(customer);

        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstname());
        assertEquals(NAME, customerDTO.getLastname());
    }
}