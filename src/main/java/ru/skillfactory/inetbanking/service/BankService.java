package ru.skillfactory.inetbanking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillfactory.inetbanking.entity.User;
import ru.skillfactory.inetbanking.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class BankService {

    private final UserRepository userRepository;

    public void addbalance(User user){
        userRepository.save(user);

    }
    public int getBalance(int id){
        User user = userRepository.getById(id);
        return user.getBalance();

    }

}
