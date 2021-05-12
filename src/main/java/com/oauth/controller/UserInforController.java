package com.oauth.controller;

import com.oauth.service.UserService;
import com.oauth.vo.ResponseMessage;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = { "用户" })
@Controller
@RequestMapping("/userInfor")
public class UserInforController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveAndUpdateUserInfor")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdateUserInfor(@ApiParam(value = "{\n" + "\"userId\": \"用户id,判断新增或修改\",\n"
            + "\"username\": \"账户名称\",\n" + "\"password\": \"明文\",\n" + "\"status\": \"状态\",\n"
            + "\"password\": \"明文\",\n" + "\"realname\": \"用户真实性名\",\n" + "\"phone\": \"电话\",\n"
            + "\"email\": \"邮箱\",\n" + "\"department\": [\n" + "{\n" + "\"departmentId\": \"部门id\",\n"
            + "\"departmentUserId\": \"用户所属部门关系id\"\n" + "}\n" + "],\n" + "\"post\": [\n" + "{\n"
            + "\"postId\": \"岗位id\",\n" + "\"postUserId\": \"岗位与用户关系id\"\n" + "}\n" + "]\n" + "\"parent\": [\n" + "{\n"
            + "\"userParentId\": \"上级用户id\",\n" + "\"parentUserId\": \"用户与用户关系id\"\n" + "}\n" + "]\n" + "\"role\": [\n"
            + "{\n" + "\"roleId\": \"角色id\",\n" + "\"userRoleId\": \"用户与角色关系id\"\n" + "}\n" + "]\n"
            + "}", required = true, type = "JSONObject") @RequestBody String jsonObjectStr) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            JSONObject jsonObject = new JSONObject(jsonObjectStr);
            userService.saveAndUpdateUserInfor(jsonObject);
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

    @GetMapping("/selectUserInfor")
    @ApiOperation(value = "查询用户数据", httpMethod = "GET")
    @ResponseBody
    public ResponseMessage selectUserInfor(
            @ApiParam(value = "用户状态1:启用,2:禁用", required = false, name = "status") @RequestParam(name = "status", required = false) Integer status,
            @ApiParam(value = "用户名称", required = false, name = "username") @RequestParam(name = "username", required = false) String username,
            @ApiParam(value = "用户Id", required = false, name = "userId") @RequestParam(name = "userId", required = false) Long userId,
            @ApiParam(value = "用户真实性名", required = false, name = "realname") @RequestParam(name = "realname", required = false) String realname,
            @ApiParam(value = "上级Id", required = false, name = "parentId") @RequestParam(name = "parentId", required = false) Long parentId,
            @ApiParam(value = "上级真实性名", required = false, name = "parentRealname") @RequestParam(name = "parentRealname", required = false) String parentRealname,
            @ApiParam(value = "邮箱", required = false, name = "email") @RequestParam(name = "email", required = false) String email,
            @ApiParam(value = "手机", required = false, name = "phone") @RequestParam(name = "phone", required = false) String phone,
            @ApiParam(value = "部门Id", required = false, name = "departmentId") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "部门", required = false, name = "department") @RequestParam(name = "department", required = false) String department,
            @ApiParam(value = "岗位Id", required = false, name = "postId") @RequestParam(name = "postId", required = false) Long postId,
            @ApiParam(value = "岗位", required = false, name = "post") @RequestParam(name = "post", required = false) String post,
            @ApiParam(value = "岗位代码", required = false, name = "postCode") @RequestParam(name = "postCode", required = false) String postCode) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
            responseMessage.setData(userService.selectUserInfor(status, username, userId, realname, parentId,
                    parentRealname, email, phone, departmentId, department, postId, post, postCode));
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }
}
