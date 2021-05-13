package com.oauth.controller;

import com.oauth.receiving.RoleRece;
import com.oauth.service.RoleService;
import com.oauth.vo.ResponseMessage;
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

@Api(tags = { "角色" })
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/saveAndUpdateRole")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdateRole(@RequestBody RoleRece roleRece) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            roleService.saveAndUpdateRole(roleRece);
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

    @GetMapping("/selectRole")
    @ApiOperation(value = "获取角色信息", httpMethod = "GET")
    @ResponseBody
    public ResponseMessage selectRole(
            @ApiParam(value = "角色Id", required = false, name = "roleId") @RequestParam(name = "roleId", required = false) Long roleId,
            @ApiParam(value = "角色名称", required = false, name = "roleName") @RequestParam(name = "roleName", required = false) String roleName) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            responseMessage.setData(roleService.selectRole(roleId, roleName));
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        }catch(Exception e){
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

}
