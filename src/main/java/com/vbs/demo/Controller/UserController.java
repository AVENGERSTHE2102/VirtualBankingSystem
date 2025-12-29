package com.vbs.demo.Controller;
import com.vbs.demo.dto.DisplayDto;
import com.vbs.demo.dto.LoginDto;
import com.vbs.demo.dto.TransactionDto;
import com.vbs.demo.dto.WithdrawDto;
import com.vbs.demo.models.History;
import com.vbs.demo.models.Transaction;
import com.vbs.demo.models.User;
import com.vbs.demo.repositories.HistoryRepo;
import com.vbs.demo.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class UserController {
     @Autowired
    UserRepo userRepo;
     @Autowired
     HistoryRepo historyRepo;
    @PostMapping("/register")
    public String register(@RequestBody User user)
    {
        userRepo.save(user);
        return "Signup Successful!!!";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDto u)
    {
        User user = userRepo.findByUsername(u.getUsername());
        if(user==null)
        {
            return "User Not found";
        }
        if(!u.getPassword().equals(user.getPassword()))
        {
            return "Incorrect Password";
        }
        if(!u.getRole().equals(user.getRole()))
        {
            return "Incorrect role";
        }
        return String.valueOf(user.getId());
    }
    @GetMapping("/get-details/{id}")
    public DisplayDto display(@PathVariable int id)
    {
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        DisplayDto displayDto = new DisplayDto(user.getUsername(),user.getBalance());
        return displayDto;

    }
    @PostMapping("/add/{adminId}")
    public String add(@RequestBody User user,@PathVariable int adminId)
    {
        History history = new History();
        history.setDescription("added by admin id: " + adminId +"added"+user.getUsername());
        historyRepo.save(history);

        userRepo.save(user);


        Transaction t = new Transaction();
        t.setAmount(user.getBalance());
        t.setCurrBalance(user.getBalance());
        t.setDescription("rs "+user.getBalance()+"Deposited Successfully");
        t.setUserId(user.getId());
        return "Added Successfully";
    }
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam String sortBy,@RequestParam String order)
    {
        Sort sort ;
        if(order.equals("desc"))
        {
            sort = Sort.by(sortBy).descending();
        }
        else {
            sort = Sort.by(sortBy).ascending();
        }

        return userRepo.findAllByRole("Customer",sort);
    }
    @GetMapping("/users/{keyword}")
    public List<User> getuser(@PathVariable String keyword)
    {
        return userRepo.findByUsernameContainingIgnoreCaseAndRole(keyword ,"customer");
    }
    @DeleteMapping("/delete-user/{userId}/admin/{adminId}")
    public String delete(@PathVariable int userId,@PathVariable int adminId){
        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("not found"));
        if(user.getBalance()>0){
            return  "Balance should be zero";
        }
        userRepo.delete(user);
        History h1 = new History();
        h1.setDescription("Admin"+adminId+"Deleted User"+user.getUsername());
        historyRepo.save(h1);

        return "Record Deleted Successfully";
    }
}
