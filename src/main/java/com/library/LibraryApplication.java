package com.library;

import com.library.auth.entity.Role;
import com.library.auth.entity.User;
import com.library.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class LibraryApplication {

    private final UserRepository userRepository;

    public LibraryApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Component
    public class AdminLoader implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            User adminFromDb = userRepository.findByUsername("admin");
            User newAdmin = new User();
            if (adminFromDb == null) {
                newAdmin.setUsername("admin");
                newAdmin.setPassword("admin");
                newAdmin.setRole(Role.ADMIN);
                userRepository.save(newAdmin);
            }
        }
    }

}
