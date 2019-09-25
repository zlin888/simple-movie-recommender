package com.cloudservice.movierecommender.util;

import com.cloudservice.movierecommender.model.bean.Ratings;
import com.cloudservice.movierecommender.model.mapper.RatingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUtil {

    @Autowired
    private RatingsMapper ratingMapper;

    public File getParsedDataFile(){
        List<Ratings> ratings = ratingMapper.selectAll();
        String path = "ratings.txt";
        FileWriter writer = null;
        try {
            writer = new FileWriter(path, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> data = new ArrayList<String>();
        String temp;
        for(Ratings rating : ratings){
            temp = Integer.toString((int)rating.getUserId())+","+Integer.toString((int)rating.getFilmId())+","+Double.toString(rating.getRating());
            data.add(temp);
        }
        try {
            for(String element : data){
                writer.write(element);
                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(path);
        return file;
    }
}
