package com.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.oauth.service.DepartmentService;
import com.oauth.vo.ResponseMessage;

import org.json.JSONObject;

@Api(tags = { "部门" })
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/saveAndUpdateDepartment")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdateDepartment(@ApiParam(value = "{\n" + "\"departmentId\": \"部门id,判断新增或修改\",\n"
            + "\"departmentName\": \"部门名称\",\n" + "\"sort\": \"排序\",\n" + "\"status\": \"状态\",\n" + "\"leader\": [\n"
            + "{\n" + "\"userId\": \"用户id\",\n" + "\"departmentLeaderId\": \"用户与部门关系Id判断新增或修改领导人\"\n" + "}\n" + "],\n"
            + "\"parent\": [\n" + "{\n" + "\"departmentRelationshipId\": \"部门与部门关系Id判断是新增或修改上下部门关系\",\n"
            + "\"parentId\": \"上级部门Id\",\n" + "\"parentPath\": \"上级部门路径\"\n" + "}\n" + "]\n"
            + "}", required = true, type = "JSONObject") @RequestBody String jsonObjectStr) {
        ResponseMessage responseMessage = new ResponseMessage();
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        try {
            departmentService.saveAndUpdateDepartment(jsonObject);
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

    @GetMapping("/selectDepartment")
    @ApiOperation(value = "获取部门信息", httpMethod = "GET")
    @ResponseBody
    public ResponseMessage selectDepartment(
            @ApiParam(value = "部门状态1:启用,2:禁用", required = false, name = "status") @RequestParam(name = "status", required = false) Integer status,
            @ApiParam(value = "部门名称", required = false, name = "departmentName") @RequestParam(name = "departmentName", required = false) String departmentName,
            @ApiParam(value = "部门id", required = false, name = "departmentId") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "上级部门id", required = false, name = "parentId") @RequestParam(name = "parentId", required = false) Long parentId,
            @ApiParam(value = "上级部门名称", required = false, name = "parentName") @RequestParam(name = "parentName", required = false) String parentName,
            @ApiParam(value = "负责人id", required = false, name = "userId") @RequestParam(name = "userId", required = false) Long userId,
            @ApiParam(value = "负责人性名", required = false, name = "username") @RequestParam(name = "username", required = false) String username,
            @ApiParam(value = "查询子项目深度,-1为全部树状结构", required = false, name = "needChild") @RequestParam(name = "needChild", required = false, defaultValue = "1") Integer needChild) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setData(departmentService.selectDepartment(status, departmentName, departmentId, parentId,
                parentName, userId, username, needChild));
        return responseMessage;
    }
}
