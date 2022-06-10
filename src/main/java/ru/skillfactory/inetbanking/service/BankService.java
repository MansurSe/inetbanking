package ru.skillfactory.inetbanking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.skillfactory.inetbanking.entity.Operations;
import ru.skillfactory.inetbanking.entity.User;
import ru.skillfactory.inetbanking.repo.OperationsRepository;
import ru.skillfactory.inetbanking.repo.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final UserRepository userRepository;
    private final OperationsRepository operationsRepository;

    public void addbalance(User user) {

        var userE = userRepository.findById(user.getId());
        if (!userE.isPresent()) {
            return;
        }
        var userE2 = userE.get();
        userE2.setBalance(userE2.getBalance() + user.getBalance());
        userRepository.save(userE2);

        // добавляем данные в таблицу operations
        var operation = new Operations();
        operation.setBalance(user.getBalance());
        operation.setTrans_date(LocalDateTime.now());
        operation.setOperation_type(1); // 1- тип операции putMoney
        operation.setUsers_id(user.getId());
        operationsRepository.save(operation);
    }

    public void takeBalance(User user) {

        var userT = userRepository.findById(user.getId());
        if (!userT.isPresent()) {
            return;
        }
        var userT2 = userT.get();
        userT2.setBalance(userT2.getBalance() - user.getBalance());
        userRepository.save(userT2);

        // добавляем данные в таблицу operation
        var operation2 = new Operations();
        operation2.setBalance(user.getBalance());
        operation2.setTrans_date(LocalDateTime.now());
        operation2.setOperation_type(2); // 2- тип операции takeMoney
        operation2.setUsers_id(user.getId());
        operationsRepository.save(operation2);

    }


    public int getBalance(Long id) {
        User user = userRepository.getById(id);
        return user.getBalance();

    }


    public List<Operations> getOperationList(Long user_id, LocalDate dateFrom, LocalDate dateTo) {

        return operationsRepository.getOperations(user_id, dateFrom.atStartOfDay(), dateTo.atStartOfDay());
    }


    public List<Operations> getOperationsWithoutDate(Long user_id) {
        return operationsRepository.getOperationsWithoutDate(user_id);

    }


    public void transferMoney(Long id1, Long id2, int balance) {
        var user1 = userRepository.findById(id1).get();
        var user2 = userRepository.findById(id2).get();


        user1.setBalance(user1.getBalance() - balance);
        userRepository.save(user1);
        user2.setBalance(user2.getBalance() + balance);
        userRepository.save(user2);

    }

}
