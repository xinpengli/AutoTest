package com.taishite.autotest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.taishite.autotest.common.FileUtile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/res")
public class weather {
    @ResponseBody
//    @RequestMapping(value = "/getWeather",method = RequestMethod.GET)
    @RequestMapping(value = "/getWeather")
    public JSONObject getWeather(@RequestBody  Map<String, String> requestMap){
        Map<String,String> result= new HashMap<>();
        String city= requestMap.get("cityId");
        String userId= requestMap.get("userId");
        JSONObject jsonObject= new JSONObject();
        if (("test").equals(userId)){
            //String path1 = FileUtile.class.getClassLoader().getResource("/").getPath();
           // String path = FileUtile.class.getClassLoader().getResource("json.txt").getPath();
          //  System.out.println(path);
            if (!(city.equals("北京") ||city.equals("天津")||city.equals("上海"))){
                jsonObject.put("msg","暂不支持该城市");
                jsonObject.put("code","10000");
                return jsonObject;

            }


            //从跟目录(编译后的target下class目录就是根目录)/获取resource下的json.txt文件的url,需要跟pom中指定class目录， 不能把资源文件打包到整体jar里，否则报找不到文件
            //注意都把配置文件放在config下否则，pom复制资源不准，导致jar包起来时
            URL url= this.getClass().getResource("/config/weather/json.txt");
           String weatherString= FileUtile.readJsonFile(url.getPath());
             jsonObject = JSON.parseObject(weatherString);
             //修改json值

            String newJson = JsonPath.parse(jsonObject.toString()).set("$.result.today.city", city).jsonString();
            jsonObject = JSON.parseObject(newJson);
            return jsonObject;
        }else {


            jsonObject.put("msg","用戶名非法");
            jsonObject.put("code","10001");
        }
       return jsonObject;
    }


}
