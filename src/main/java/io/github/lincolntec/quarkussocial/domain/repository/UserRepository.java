package io.github.lincolntec.quarkussocial.domain.repository;

import io.github.lincolntec.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
