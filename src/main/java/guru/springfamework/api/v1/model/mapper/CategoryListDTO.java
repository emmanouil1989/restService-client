package guru.springfamework.api.v1.model.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
