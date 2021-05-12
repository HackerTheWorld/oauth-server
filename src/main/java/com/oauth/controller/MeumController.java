package com.oauth.controller;

import com.oauth.vo.ResponseMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "菜单" })
@Controller
@RequestMapping("/meum")
public class MeumController {

    @PostMapping("/saveAndUpdateMeum")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdateMeum(@ApiParam(value = "{\n" + "\"meumId\":\"菜单Id,判断新增或修改\",\n" + "\"meumName\":\"菜单名称\",\n"
                    + "\"url\":\"菜单地址\",\n" + "\"parentId\":\"父亲菜单名称,跟菜单为0\",\n" + "\"meumType\":\"菜单类型,M:菜单,B:按钮\",\n"
                    + "\"perms\":\"权限对应的岗位,多个岗位用|分割\",\n" + "\"roleIds\":[\n" + "\"角色id\",\"\"\n" + "]\n"
                    + "}", required = true, type = "JSONObject") @RequestBody String jsonObjectStr) {
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }
}
