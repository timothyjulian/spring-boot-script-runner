package me.timothy.springbootscriptrunner.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    /**
     * Get content from given path of the file from project root directory
     *
     * @param pathFromRoot
     * @return
     */
    public String getContentFromFile(String pathFromRoot) {
        // Your script stored as a string
        String projectDirectory = System.getProperty("user.dir") + "/src/main/java/me/timothy/springbootscriptrunner";
        String fileProject = projectDirectory + pathFromRoot;
        File file = new File(fileProject);

        String content = "";
        try {
            // Read the entire contents of sample.txt
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
