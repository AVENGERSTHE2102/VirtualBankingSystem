package com.vbs.demo.Controller;

import com.vbs.demo.models.History;
import com.vbs.demo.repositories.HistoryRepo;
import com.vbs.demo.repositories.UserRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HistoryController {

@Autowired
    private final HistoryRepo historyRepo;

    public HistoryController(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }
    @GetMapping("/histories")
    public List<History> history()
    {
        return historyRepo.findAll();
    }
}
