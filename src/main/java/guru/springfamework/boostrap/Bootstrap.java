package guru.springfamework.boostrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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
        saveCustomers();
    }

    private void saveCustomers() {
        Customer manos = Customer.builder()
                .fristname("manos")
                .lastname("manos")
                .build();

        Customer nef = Customer.builder()
                .fristname("nef")
                .lastname("nef")
                .build();

        Customer xenia = Customer.builder()
                .fristname("xenia")
                .lastname("xenia")
                .build();

        Customer georgina = Customer.builder()
                .fristname("georgina")
                .lastname("georgina")
                .build();

        Customer lol = Customer.builder()
                .fristname("lol")
                .lastname("lol")
                .build();
        customerRepository.save(manos);
        customerRepository.save(nef);
        customerRepository.save(xenia);
        customerRepository.save(georgina);
        customerRepository.save(lol);
        log.info("Data customerRepository loaded = {}",customerRepository.count());
    }
}
