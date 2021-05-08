package com.oauth.controller;

import com.oauth.vo.ResponseMessage;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/userInfor")
public class UserInforController {

    @PostMapping("/saveAndUpdateUserInfor")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdateUserInfor(@ApiParam(value = "{\n" + "\"userId\": \"用户id,判断新增或修改\",\n"
            + "\"username\": \"账户名称\",\n" + "\"password\": \"明文\",\n" + "\"status\": \"状态\",\n"
            + "\"password\": \"明文\",\n" + "\"realname\": \"用户真实性名\",\n" + "\"phone\": \"电话\",\n"
            + "\"email\": \"邮箱\",\n" + "\"department\": [\n" + "{\n" + "\"departmentId\": \"部门id\",\n"
            + "\"departmentUserId\": \"用户所属部门关系id\"\n" + "}\n" + "],\n" + "\"post\": [\n" + "{\n"
            + "\"postId\": \"岗位id\",\n" + "\"postUserId\": \"岗位与用户关系id\"\n" + "}\n" + "]\n"
            + "}", required = true, type = "JSONObject") @RequestBody String jsonObjectStr) {
        ResponseMessage responseMessage = new ResponseMessage();
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        try {

            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }
}
