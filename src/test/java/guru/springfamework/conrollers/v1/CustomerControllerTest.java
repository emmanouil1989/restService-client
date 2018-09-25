package guru.springfamework.conrollers.v1;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.CategoryService.CustomerService;
import guru.springfamework.EntitiesAbstract;
import guru.springfamework.JsonHelper;
import guru.springfamework.api.v1.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class CustomerControllerTest extends EntitiesAbstract {

    private static final String API_V1_CUSTOMERS = "/api/v1/customers";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getCustomers() throws Exception {

        when(customerService.getAllCustomers()).thenReturn(getCustomersDTO());

        mockMvc.perform(get("/api/v1/customers")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));

    }

    @Test
    public void getCustomerById() throws Exception {

        when(customerService.getCustomerById(any())).thenReturn(getCustomer1DTO());

        mockMvc.perform(get("/api/v1/customers/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname",equalTo(NAME)));
    }

    @Test
    public void createCustomer() throws Exception {

        when(customerService.createCustomer(any())).thenReturn(getCustomer2DTO());

        mockMvc.perform(post(API_V1_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(getCustomer1DTO())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FRISTNAME)));
    }

    @Test
    public void updateCustomer() throws Exception {
        when(customerService.updateCustomer(any(),any())).thenReturn(getCustomer2DTO());

        mockMvc.perform(put(API_V1_CUSTOMERS + "/2")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonHelper.toJson(getCustomer2DTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FRISTNAME)));


    }

    @Test
    public void patchCustomer() throws Exception {
        CustomerDTO localCustomer = CustomerDTO.builder().firstname("manos").build();

        CustomerDTO customer1DTO = getCustomer1DTO();
        customer1DTO.setFirstname(localCustomer.getFirstname());
        when(customerService.patchCustomer(anyLong(),any())).thenReturn(customer1DTO);

        mockMvc.perform(patch(API_V1_CUSTOMERS + "/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonHelper.toJson(localCustomer)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname", equalTo("manos")));
    }
}