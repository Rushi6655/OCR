package com.imageextraction.imageextraction.Controller;

import com.imageextraction.imageextraction.Entity.MpaWorkloadImageTextRecordR4;
import com.imageextraction.imageextraction.Entity.MpaWrkloadImageTextR4;
import com.imageextraction.imageextraction.Entity.RawData;
import com.imageextraction.imageextraction.Repo.MpaWorkloadImageTextRecordR4Repo;
import com.imageextraction.imageextraction.Repo.MpaWrkloadImageTextR4Repo;
import com.imageextraction.imageextraction.Repo.RawDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.*;

@RestController
@RequestMapping("/regex")
public class ExtractionOps {
    @Autowired
    private RawDataRepo repo;
    @Autowired
    private MpaWorkloadImageTextRecordR4Repo recordRepo;
    @Autowired
    private MpaWrkloadImageTextR4Repo mainrepo;
    @GetMapping("/extractusers")
    public Map<MpaWrkloadImageTextR4,List<String>> getAllUsersData(){
        List<String[]> result=new ArrayList<>();
        List<List<String>> result1=new ArrayList<>();
        Map<MpaWrkloadImageTextR4,List<String>> resultmap=new LinkedHashMap<>();
        List<MpaWrkloadImageTextR4> alldata=mainrepo.findAll();
        int count=0;

        for (MpaWrkloadImageTextR4 d:alldata) {

            String pagedata=d.getImageText();
            String regex = "(?=(([a-z A-Z 0-9])*?)(Ms|Mrs|Mr|Dr))";
            String [] str = pagedata.split(regex);
            List<String> finalResult=new ArrayList<>();
            String temp="";
            boolean isContinue=false;
            for (String s:str) {
                try {
                    Integer.parseInt(s);
                    temp+=s;
                }catch (Exception e){
                    if((s.length()==1 || s.equals(" ")) ){
                        temp+=s;
                    }else {
                        String ss=temp;
                        if(ss.trim().length()==0){
                            temp="";
                            if(isContinue){
                                String last=finalResult.get(finalResult.size()-1);
                                last=last+" "+s;
                                finalResult.set(finalResult.size()-1,last);
                            }else{

                                finalResult.add(s);

                            }
                        }else{
                            finalResult.add(temp+" "+s);
                            isContinue=true;
                            temp="";
                        }
                    }

                }
            }
            resultmap.put(d,finalResult);
            result1.add(finalResult);
        }

        int ptr=1;
        for ( Map.Entry<MpaWrkloadImageTextR4,List<String>> i:resultmap.entrySet()) {
            String fileId=i.getKey().getWorkloadId();
            String recordId=fileId+"_R";
            for (String s:i.getValue()){
                MpaWorkloadImageTextRecordR4 record=new MpaWorkloadImageTextRecordR4();
                record.setWorkLoadUnitTextR4Id(recordId+ptr);
                record.setCreatedBy(1);
                record.setRecordText(s);
                record.setWorkloadImageText(i.getKey());
                recordRepo.save(record);
                ptr++;
            }
        }

        return resultmap;
    }
}
