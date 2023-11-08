package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Branch;
import com.bezkoder.springjwt.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class BranchController {
    @Autowired
    private BranchRepository branchRepository;

    @GetMapping("/branch")
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

}
