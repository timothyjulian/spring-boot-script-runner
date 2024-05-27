package me.timothy.springbootscriptrunner.runner.python;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import me.timothy.springbootscriptrunner.runner.Runner;
import me.timothy.springbootscriptrunner.service.FileService;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

@Service
public class PythonRunner implements Runner {

    private final FileService fileService;

    public PythonRunner(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public JSONObject run(JSONObject input) {
        long startTime = System.currentTimeMillis();

        String content = fileService.getContentFromFile("/runner/python/script.py");

        JSONObject resultJson = new JSONObject();

        try (Context context = Context.create("python")) {
            // Set the binding
            context.getBindings("python").putMember("message", input.toJSONString());

            Value result = context.eval("python", content);
            resultJson = JSON.parseObject(result.asString());
            return resultJson;
        } finally {
            // endTime set when the result is parsed and returned
            long endTime = System.currentTimeMillis();

            resultJson.put("timecost", (endTime - startTime));
        }
    }
}
