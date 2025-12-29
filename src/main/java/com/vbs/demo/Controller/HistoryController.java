package com.vbs.demo.Controller;

import com.vbs.demo.models.History;
import com.vbs.demo.repositories.HistoryRepo;
import com.vbs.demo.repositories.UserRepo;
import jakarta.persistence.Column;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HistoryController {


    private final HistoryRepo historyRepo;

    public HistoryController(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }
    //@PostMapping("/history")
    //public List<History> History (@RequestBody String Description)
    //{

      // return ;
    //}
}
