package guru.springfamework;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;

import java.util.Arrays;
import java.util.List;

abstract public  class EntitiesAbstract {

    public static final String FRISTNAME = "manos";
    public static final String LASTNAME = "manos";

    public static final String NAME = "JIM";

    public List<Customer> getCustomerList() {
        return Arrays.asList(getCustomer(),getCustomer2());
    }

    public Customer getCustomer2() {
        return Customer.builder()
                .id(1L)
                .lastname(LASTNAME)
                .firstname(FRISTNAME)
                .build();
    }

    public Customer getCustomer() {
        String ouaou = "ouaou";
        return Customer.builder()
                .id(2l)
                .firstname(ouaou)
                .lastname(ouaou)
                .build();
    }

    public List<CustomerDTO> getCustomersDTO(){
        return Arrays.asList(getCustomer1DTO(),getCustomer2DTO());
    }

    public CustomerDTO getCustomer1DTO(){
        return CustomerDTO.builder()
                .firstname(NAME)
                .lastname(NAME)
                .build();
    }

    public CustomerDTO getCustomer2DTO(){
        return CustomerDTO.builder()
                .lastname(LASTNAME)
                .firstname(FRISTNAME)
                .build();
    }


    public List<CategoryDTO> getCategories() {
        CategoryDTO category1  = getCategory1();
        CategoryDTO category2 = getCategory2();
        return Arrays.asList(category1,category2);
    }

    public CategoryDTO getCategory2() {
        return CategoryDTO.builder()
                .id(1l)
                .name("BOB")
                .build();
    }

    public CategoryDTO getCategory1() {
        return CategoryDTO.builder()
                .id(1l)
                .name(NAME)
                .build();
    }

}
