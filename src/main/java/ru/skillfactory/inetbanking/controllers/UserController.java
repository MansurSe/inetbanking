package ru.skillfactory.inetbanking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.inetbanking.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.skillfactory.inetbanking.repo.UserRepository;
import ru.skillfactory.inetbanking.service.BankService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public String getBalance(@PathVariable int id){
        try{
            return bankService.getBalance(id) + "";


        }catch (Exception e) {
            return "ID not found" + e;
        }

    }

    @PutMapping("/putMoney")
    public String putMoney(@RequestBody User user){
        int balance =0;

        try{
            balance  = bankService.getBalance(user.getId());

        }catch (Exception e){
                return "ID not found" + e;
        }
        int bal = user.getBalance();

          if(bal<0) {
                return bal + "Error during operations";
        }
        user.setBalance(balance + bal);
        bankService.addbalance(user);
        return "Success";

    }

    @PutMapping("/takeMoney")

    public String takeMoney(@RequestBody User user){
        int balance =0;

        try{
            balance  = bankService.getBalance(user.getId());

        }catch (Exception e){
            return "ID not found" + e;
        }
        int bal = user.getBalance();
        if(bal<0) {
            return bal + "Balance not enough";
        }
        user.setBalance(balance - bal);
        bankService.addbalance(user);
        return "Success";

    }

}

