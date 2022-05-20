package ru.skillfactory.inetbanking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.inetbanking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
