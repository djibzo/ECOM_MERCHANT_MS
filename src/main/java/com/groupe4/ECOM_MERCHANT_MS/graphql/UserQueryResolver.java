package com.groupe4.ECOM_MERCHANT_MS.graphql;

import com.groupe4.ECOM_MERCHANT_MS.entities.User;
import com.groupe4.ECOM_MERCHANT_MS.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

@Controller
public class UserQueryResolver {

    private final UserRepository repo;

    public UserQueryResolver(UserRepository repo) {
        this.repo = repo;
    }

    @QueryMapping
    public List<User> users() {
        return repo.findAll();
    }

    @QueryMapping
    public User user(@Argument Long id) {
        return repo.findById(id).orElse(null);
    }
}
