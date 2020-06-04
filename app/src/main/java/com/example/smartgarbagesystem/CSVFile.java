package com.example.smartgarbagesystem;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFile {
    InputStream inputStream;
    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(){
        List resultList = new ArrayList();
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            for(;;){
                String[] substuff = reader.readNext();
                if(substuff!=null){
                    ArrayList<String> rowArray = new ArrayList<String>(Arrays.asList(substuff));
                    resultList.add(rowArray);
                }
                else{
                    break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return resultList;
    }
}


