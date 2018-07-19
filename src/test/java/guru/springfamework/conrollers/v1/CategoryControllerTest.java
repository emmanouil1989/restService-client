package guru.springfamework.conrollers.v1;

import guru.springfamework.CategoryService.CategoryService;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private static final String NAME = "JIM";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void getAllCategories() throws Exception {
        List<CategoryDTO> categories = getCategories();

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories",hasSize(2)));
    }

    @Test
    public void getCategoryByName() throws Exception {
        CategoryDTO category1 = getCategory1();

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get("/api/v1/categories/JIM")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));
    }

    private List<CategoryDTO> getCategories() {
        CategoryDTO category1  = getCategory1();
        CategoryDTO category2 = getCategory2();
        return Arrays.asList(category1,category2);
    }

    private CategoryDTO getCategory2() {
        return CategoryDTO.builder()
                .id(1l)
                .name("BOB")
                .build();
    }

    private CategoryDTO getCategory1() {
        return CategoryDTO.builder()
                .id(1l)
                .name(NAME)
                .build();
    }
}