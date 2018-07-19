package guru.springfamework.CategoryService;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.mapper.CategoryMapper;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> all = categoryRepository.findAll();

        return all.stream()
                .map(category -> categoryMapper.categoryToCategoryDTO(category))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDTO getCategoryByName(String name) {

        Category category = categoryRepository.findByName(name);

        return categoryMapper.categoryToCategoryDTO(category);

    }
}
