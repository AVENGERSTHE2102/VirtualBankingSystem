package com.vbs.demo.Controller;

import com.vbs.demo.dto.TransactionDto;
import com.vbs.demo.dto.TransferDto;
import com.vbs.demo.dto.UpdateDto;
import com.vbs.demo.models.Transaction;
import com.vbs.demo.models.User;
import com.vbs.demo.repositories.TransactionRepo;
import com.vbs.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TransactionRepo transactionrepo;
    @PostMapping("/deposit")
    public String Deposit(@RequestBody TransactionDto u)
    {
        User user = userRepo.findById(u.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
    double newBalance = user.getBalance() + u.getAmount();
    user.setBalance(newBalance);
    userRepo.save(user);

    Transaction t = new Transaction();
    t.setAmount(u.getAmount());
    t.setCurrBalance(newBalance);
    t.setDescription("rs "+u.getAmount()+"Deposited Successfully");
    t.setUserId(u.getId());

    transactionrepo.save(t);
        return "Deposited successfullyy";
    }
    @PostMapping("/withdraw")
    public String Withdraw(@RequestBody TransactionDto u)
    {
        User user = userRepo.findById(u.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
        if(user.getBalance()>=u.getAmount()) {
            double newBalance = user.getBalance() - u.getAmount();
            user.setBalance(newBalance);
            userRepo.save(user);

            Transaction t = new Transaction();
            t.setAmount(u.getAmount());
            t.setCurrBalance(newBalance);
            t.setDescription("rs " + u.getAmount() + "Withdrawal Successfully");
            t.setUserId(u.getId());

            transactionrepo.save(t);
            return "Withdrawal successfullyy";
        }
        return "Balance InSufficient";
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferDto obj)
    {
        User sender = userRepo.findById(obj.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
        User rec = userRepo.findByUsername(obj.getUsername());

        if(rec==null) return "Receiver not found";
        if(sender.getId()==rec.getId()) return "Self transfer not allowed";
        if(obj.getAmount()<1) return"Invalid amount";

        double newBalance = sender.getBalance() - obj.getAmount();
        if(newBalance<0) return "Insufficient Balance";
        double rBalance = rec.getBalance() + obj.getAmount();
        rec.setBalance(rBalance);
        sender.setBalance(newBalance);
        userRepo.save(sender);
        userRepo.save(rec);

        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        t1.setAmount(obj.getAmount());
        t1.setCurrBalance(newBalance);
        t1.setDescription("rs "+obj.getAmount()+"Transferred  Successfully to"+rec.getUsername());
        t1.setUserId(sender.getId());

        t2.setAmount(obj.getAmount());
        t2.setCurrBalance(rBalance);
        t2.setDescription("rs "+obj.getAmount()+"received  Successfully from "+sender.getUsername());
        t2.setUserId(rec.getId());

        transactionrepo.save(t1);
        transactionrepo.save(t2);

        return "Transfer done successfully";


    }
    @GetMapping("/passbook/{id}")
    public List<Transaction> getpassbook(@PathVariable int id)
    {
        return transactionrepo.findAllByUserId(id);
    }
    @PostMapping("/update")
    public String update(@RequestBody UpdateDto obj)
    {
        User user = userRepo.findById(obj.getId()).orElseThrow(()-> new  RuntimeException("not found"));
        if(obj.getKey().equalsIgnoreCase("name"))
        {
            if(user.getName().equals(obj.getValue()))
            {
                return "Cant be same";
            }
            user.setName(obj.getValue());
        }
        else if(obj.getKey().equalsIgnoreCase("password"))
        {
            if(user.getPassword().equals(obj.getValue()))
            {
                return "Cant be same";
            }
            user.setPassword(obj.getValue());
        }
        else if(obj.getKey().equalsIgnoreCase("email"))
        {
            if(user.getEmail().equals(obj.getValue()))
            {
                return "Cant be same";
            }
            User cmp = userRepo.findAllByEmail(obj.getValue());
            if(cmp!=null)
            {
                return "email already exits";
            }
            user.setEmail(obj.getValue());
        }
        else {
            return "Invalid key";
        }

        userRepo.save(user);
        return "Updated Successfully";
    }

}
