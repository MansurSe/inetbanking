package ru.skillfactory.inetbanking.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.inetbanking.entity.Operations;
import ru.skillfactory.inetbanking.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.skillfactory.inetbanking.repo.UserRepository;
import ru.skillfactory.inetbanking.service.BankService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final BankService bankService;


    @GetMapping("/getUsers")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getBalance/{id}")
    public String getBalance(@PathVariable long id) {
        try {
            return "balance: " + bankService.getBalance(id);


        } catch (Exception e) {
            return "ID not found" + e;
        }

    }

    @PutMapping("/putMoney")
    public String putMoney(@RequestBody User user) {

        bankService.addbalance(user);
        return "Success";

    }

    @PutMapping("/takeMoney")

    public String takeMoney(@RequestBody User user) {
        /*int balance;

        try{
            balance  = bankService.getBalance(user.getId());

        }catch (Exception e){
            return "ID not found" + e;
        }
        int bal = user.getBalance();
        if(bal<0) {
            return bal + "Balance not enough";
        }
        user.setBalance(balance - bal);*/
        bankService.takeBalance(user);
        return "Success";

    }

    @GetMapping("/operations")
    public List<Operations> getOperationList(@RequestParam Long user_id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom, @RequestParam
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo) {

        return bankService.getOperationList(user_id, dateFrom, dateTo);
    }

}

