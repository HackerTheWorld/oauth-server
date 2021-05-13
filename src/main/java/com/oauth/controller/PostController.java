package com.oauth.controller;

import com.oauth.entity.PostEntity;
import com.oauth.service.PostService;
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

@Controller
@Api(tags = { "岗位" })
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/saveAndUpdatePost")
    @ApiOperation(value = "新增或修改数据", httpMethod = "POST")
    @ResponseBody
    public ResponseMessage saveAndUpdatePost(@RequestBody PostEntity postEntity) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            postService.saveAndUpdatePost(postEntity);
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }

    @GetMapping("/selectPost")
    @ApiOperation(value = "获取部门信息", httpMethod = "GET")
    @ResponseBody
    public ResponseMessage selectPost(
            @ApiParam(value = "岗位状态1:启用,2:禁用", required = false, name = "status") @RequestParam(name = "status", required = false) Integer status,
            @ApiParam(value = "岗位名称", required = false, name = "postName") @RequestParam(name = "postName", required = false) String postName,
            @ApiParam(value = "岗位代码", required = false, name = "postCode") @RequestParam(name = "postCode", required = false) String postCode,
            @ApiParam(value = "岗位Id", required = false, name = "postId") @RequestParam(name = "postId", required = false) Long postId) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            responseMessage.setData(postService.selectPost(status, postName, postCode, postId));
            responseMessage.setMess("success");
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            responseMessage.setMess(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return responseMessage;
    }
}