package me.timothy.springbootscriptrunner.runner;

import com.alibaba.fastjson.JSONObject;

public interface Runner {

    JSONObject run(JSONObject input);
}
