package guru.springfamework.boostrap;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = Category.builder()
                .name("Fruits")
                .build();

        Category dried = Category.builder()
                .name("Dried")
                .build();

        Category fresh = Category.builder()
                .name("Fresh")
                .build();

        Category exotic = Category.builder()
                .name("Exotic")
                .build();

        Category nuts = Category.builder()
                .name("nuts")
                .build();
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        log.info("Data loaded = {}",categoryRepository.count());
    }
}
