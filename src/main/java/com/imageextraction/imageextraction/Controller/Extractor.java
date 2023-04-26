package com.imageextraction.imageextraction.Controller;

import com.imageextraction.imageextraction.Entity.RawData;
import com.imageextraction.imageextraction.Repo.RawDataRepo;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/data")
public class Extractor {
    @Autowired
    private RawDataRepo repo;
    @PostMapping("/extract")
    public String extractImages(){
        ITesseract tesseract=new Tesseract();
        try {
            tesseract.setDatapath("C:\\Users\\Hrushikesh Shelke\\Desktop\\Image Extraction\\tessdata-main");
            tesseract.setTessVariable("user_defined_dpi", "70");
            String path="C:\\Users\\Hrushikesh Shelke\\Desktop\\Image Extraction\\Workload0003.png";
            String data=tesseract.doOCR(new File(path));
            if(data.length()>0){
                RawData d=new RawData();
                d.setData(data);
                repo.save(d);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "extracting";
    }
    @GetMapping("/modify")
    public String modify(){
        String str="";
        List<RawData> alldata= repo.findAll();
        for (RawData d:alldata) {
            str+=d;
        }
        return str;
    }
}
