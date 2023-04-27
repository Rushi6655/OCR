package com.imageextraction.imageextraction.Controller;

import com.imageextraction.imageextraction.Entity.RawData;
import com.imageextraction.imageextraction.Repo.RawDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regex")
public class ExtractionOps {
    @Autowired
    private RawDataRepo repo;
    @GetMapping("/extractusers")
    public List<String[]> getAllUsersData(){
        List<String[]> result=new ArrayList<>();
        List<RawData> alldata=repo.findAll();

        for (RawData d:alldata) {
            String pagedata=d.getData();
            String regex = "((([a-z A-Z 0-9])*?)(Ms|Mrs|Mr|Dr)+)";
            String [] str = pagedata.split(regex);
            result.add(str);
        }
        return result;
    }
}
