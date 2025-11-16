package com.groupe4.ECOM_MERCHANT_MS.graphql;

import com.groupe4.ECOM_MERCHANT_MS.entities.User;
import com.groupe4.ECOM_MERCHANT_MS.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;

import java.time.LocalDateTime;

@Controller
public class UserMutationResolver {

    private final UserRepository repo;

    public UserMutationResolver(UserRepository repo) {
        this.repo = repo;
    }

    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User u = new User();
        u.setFirstName(input.firstName());
        u.setLastName(input.lastName());
        u.setPhone(input.phone());
        u.setPhone_verified(false);
        u.setUpdated_at(LocalDateTime.now());
        return repo.save(u);
    }

    public record UserInput(String firstName, String lastName, String phone) {}
}