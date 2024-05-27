package me.timothy.springbootscriptrunner.web;

import com.alibaba.fastjson.JSONObject;
import me.timothy.springbootscriptrunner.runner.groovy.GroovyRunner;
import me.timothy.springbootscriptrunner.runner.javascript.JavascriptRunner;
import me.timothy.springbootscriptrunner.runner.python.PythonRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RunnerController {

    private final GroovyRunner groovyRunner;

    private final JavascriptRunner javascriptRunner;

    private final PythonRunner pythonRunner;


    public RunnerController(GroovyRunner groovyRunner,
                            JavascriptRunner javascriptRunner,
                            PythonRunner pythonRunner) {
        this.groovyRunner = groovyRunner;
        this.javascriptRunner = javascriptRunner;
        this.pythonRunner = pythonRunner;
    }

    @PostMapping(value = "/groovy")
    public ResponseEntity<JSONObject> groovy(@RequestBody JSONObject request) {
        JSONObject response = groovyRunner.run(request);
        System.out.println(response.toJSONString());
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping(value = "/javascript")
    public ResponseEntity<JSONObject> javascript(@RequestBody JSONObject request) {
        JSONObject response = javascriptRunner.run(request);
        System.out.println(response.toJSONString());
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping(value = "/python")
    public ResponseEntity<JSONObject> python(@RequestBody JSONObject request) {
        JSONObject response = pythonRunner.run(request);
        System.out.println(response.toJSONString());
        return ResponseEntity.status(200).body(response);
    }

}
