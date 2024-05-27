package me.timothy.springbootscriptrunner.runner.javascript;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.timothy.springbootscriptrunner.runner.Runner;
import me.timothy.springbootscriptrunner.service.FileService;
import org.springframework.stereotype.Service;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;


@Service
public class JavascriptRunner implements Runner {

    private final FileService fileService;

    public JavascriptRunner(FileService fileService) {
        this.fileService = fileService;
    }

    public JSONObject run(JSONObject input) {
        long startTime = System.currentTimeMillis();

        String content = fileService.getContentFromFile("/runner/javascript/script.js");

        JSONObject resultJson = new JSONObject();
        // Initialize context
        try (Context context = Context.create("js")) {
            // Set the binding
            context.getBindings("js").putMember("message", input.toJSONString());

            Value result = context.eval("js", content);
            resultJson = JSON.parseObject(result.asString());
            return resultJson;
        } finally {
            // endTime set when the result is parsed and returned
            long endTime = System.currentTimeMillis();

            resultJson.put("timecost", (endTime - startTime));
        }
    }
}
