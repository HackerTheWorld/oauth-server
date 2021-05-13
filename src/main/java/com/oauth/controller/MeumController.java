package com.oauth.controller;

import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.service.MeumService;
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

@Api(tags = { "菜单" })
@Controller
@RequestMapping("/meum")
public class MeumController {

    @Autowired
    private MeumService meumService;

    @PostMapping("/saveAndUpdateMeum")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdateMeum(@RequestBody MeumEntityWithBLOBs meumEntity) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            meumService.saveAndUpdateMeum(meumEntity);
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

    @GetMapping("/selectMeum")
    @ApiOperation(value = "获取菜单信息", httpMethod = "GET")
    @ResponseBody
    public ResponseMessage selectMeum(
            @ApiParam(value = "菜单Id", required = false, name = "meumId") @RequestParam(name = "meumId", required = false) Long meumId,
            @ApiParam(value = "菜单名称", required = false, name = "meumName") @RequestParam(name = "meumName", required = false) String meumName,
            @ApiParam(value = "父菜单Id", required = false, name = "parentId") @RequestParam(name = "parentId", required = false) Long parentId,
            @ApiParam(value = "权限Id", required = false, name = "roleId") @RequestParam(name = "roleId", required = false) Long roleId,
            @ApiParam(value = "查询子项目深度,-1为全部树状结构", required = false, name = "needChild") @RequestParam(name = "needChild", required = false, defaultValue = "1") Integer needChild) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage.setData(meumService.selectMeum(meumId, meumName, parentId,roleId, needChild));
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }
}
