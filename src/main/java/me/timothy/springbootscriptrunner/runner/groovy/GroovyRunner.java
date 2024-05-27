package me.timothy.springbootscriptrunner.runner.groovy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import me.timothy.springbootscriptrunner.runner.Runner;
import me.timothy.springbootscriptrunner.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class GroovyRunner implements Runner {

    private final FileService fileService;

    public GroovyRunner (FileService fileService) {
        this.fileService = fileService;
    }

    public JSONObject run(JSONObject input) {
        long startTime = System.currentTimeMillis();

        String content = fileService.getContentFromFile("/runner/groovy/script.groovy");

        // Initialize the GroovyShell and set the binding
        Binding binding = new Binding();
        binding.setVariable("message", input.toJSONString());
        GroovyShell shell = new GroovyShell(binding);

        // Execute the script
        Object result = shell.evaluate(content);
        JSONObject resultJson = JSON.parseObject(result.toString());


        // endTime set where JSON is parsed
        long endTime = System.currentTimeMillis();

        resultJson.put("timecost", (endTime - startTime));
        return resultJson;
    }


}
