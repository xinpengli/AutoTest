package com.taishite.autotest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taishite.autotest.StudentEntity;
import com.taishite.autotest.StudentResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/res")
public class Student {

    @RequestMapping(value = "/getStudent",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getStudent(@RequestBody JSONObject studentId) {
//        JSONObject studentIdJO = JSON.parseObject(studentId.toString());
        if (!isHavingStudent(StudentResources.studentList,studentId.getString("studentId"))){
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("code","40001");
            jsonObject.put("msg","查询的记录不存在");
            return jsonObject;
        };
        StudentEntity studentEntity = StudentResources.studentList.stream().filter(item -> item.getStudentId().equals(studentId.getString("studentId"))).findFirst().get();
        return JSON.parseObject(JSON.toJSONString(studentEntity));
    }
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, String>  addStudent(@RequestBody JSONObject student ) {
        StudentEntity   studentEntity= student.toJavaObject(StudentEntity.class);
        if(isHavingStudent(StudentResources.studentList,studentEntity.getStudentId())){
            Map<String, String> map=  new HashMap<>();
            map.put("code","50001");
            map.put("msg","studentId 不能重复");
            return map;
        }
        StudentResources.studentList.add(studentEntity);
        Map<String, String> map=  new HashMap<>();
        map.put("code","0");
        map.put("msg","添加成功");
        return   map;
    }

    @ResponseBody
    @GetMapping(path = "index")
    public String index(HttpServletRequest request) {
        return "重定向访问! " + JSON.toJSONString(request.getParameterMap());
    }
    @GetMapping(path = "r1")
    public String r1() {
        return "redirect:/res/index?base=r1";
    }


    public static boolean isHavingStudent(List<StudentEntity> list,String studentId) {

        try {
            StudentEntity studentEntity;
            studentEntity = StudentResources.studentList.stream().filter(item -> item.getStudentId().equals(studentId)).findFirst().get();
            return true;
        }
        catch (NoSuchElementException exception) {
             return  false;
        }

    }
}
