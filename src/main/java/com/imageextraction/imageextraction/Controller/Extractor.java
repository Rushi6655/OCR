package com.imageextraction.imageextraction.Controller;

import com.imageextraction.imageextraction.Entity.MpaWrkloadImageTextR4;
import com.imageextraction.imageextraction.Entity.RawData;
import com.imageextraction.imageextraction.Repo.MpaWrkloadImageTextR4Repo;
import com.imageextraction.imageextraction.Repo.RawDataRepo;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@RestController
@RequestMapping("/data")
public class Extractor {
    @Autowired
    private RawDataRepo repo;
    @Autowired
    private MpaWrkloadImageTextR4Repo mainrepo;
    @PostMapping("/extractAll")
    public String extractImages(){
        ITesseract tesseract=new Tesseract();
        try {
            File folder = new File("C:\\Users\\Hrushikesh Shelke\\Desktop\\Image Extraction\\Workload 38,700 Records 4");
            File[] listOfFiles = folder.listFiles();
            tesseract.setDatapath("C:\\Users\\Hrushikesh Shelke\\Desktop\\Image Extraction\\tessdata-main");
            tesseract.setTessVariable("user_defined_dpi", "70");
            String baseSerial="R04_W";
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println("--");
                  BufferedImage image = ImageIO.read(file);
                    ImageIO.write(image, "jpg", file);
                    String data=tesseract.doOCR(file);
                    if(data.length()>0){
                        MpaWrkloadImageTextR4 row=new MpaWrkloadImageTextR4();
                        row.setImageText(data);
                        String id=baseSerial+(file.getName().replace("Workload",""));
                        row.setWorkloadId(id.replace(".jpg",""));
                        FileInputStream fl = new FileInputStream(file);
                        byte[] arr = new byte[(int)file.length()];
                        fl.read(arr);
                        fl.close();
                        row.setWorkingImage(arr);
                        row.setCreatedBy(1);
                        Date d=new Date();
                        row.setCreatedDate(d.toString());
                        System.out.println(row.getWorkloadId());
                        mainrepo.save(row);
                    }
                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return "extracting";
    }
    @PostMapping("/extract")
    public String extractsingle(){
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
