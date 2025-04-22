package com.kvison.weblog.web.controller;

import com.kvison.weblog.common.aspect.ApiOperationLog;
import com.kvison.weblog.common.enums.BizException;
import com.kvison.weblog.common.enums.ResponseEnum;
import com.kvison.weblog.common.utils.Response;
import com.kvison.weblog.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * @author xueliang
 * Title: TestController</p>
 * Description:</p>
 * @date 2025/04/15
 */
@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {
    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口是否通过")
    @ApiOperation(value = "接口0")
    public User test(@RequestBody User user){
        return user;
    }

    // 1，参数校验
    @PostMapping("/test1")
    @ApiOperationLog(description = "测试接口参数校验")
    public ResponseEntity<String> test1(@RequestBody @Validated User user, BindingResult bindingResult) {
        //对参数校验判断
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        //参数没问题
        return ResponseEntity.ok("Success");
    }

    //2，返回值封装
    @PostMapping("/test2")
    @ApiOperationLog(description = "测试接口返回值封装")
    public Response test2(@RequestBody @Validated User user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return Response.fail(errorMessage);
        }
        return Response.success();
    }

    //3，手动抛异常
    @PostMapping("/test3")
    @ApiOperationLog(description = "测试抛自定义业务异常")
    public Response test3(@RequestBody @Validated User user,BindingResult bindingResult){
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        throw new BizException(ResponseEnum.PRODUCT_NOT_FOND);
    }

    //4，模拟系统抛异常
   @PostMapping("/test4")
   @ApiOperationLog(description = "测试抛系统异常")
    public Response test4(@RequestBody @Validated User user,BindingResult bindingResult){
        // 主动定义一个运行时异常，分母不能为零
        int a = 1/0;
        return Response.success();
    }

    //5，测试MethodArgumentNotValidException异常
    // 与1进行对比：1是手动处理参数异常，此处是通过封装GlobalExceptionHandler统一处理异常信息
    // 如果没有参数异常问题，则返会success。否则调用GlobalExceptionHandler中捕获到MethodArgumentNotValidException异常的处理方法。既返回方法中的fail
    // 此处用浏览做了一下测试，访问网址为http://localhost:8080/test5，因浏览器调用的是get请求，因此会报系统异常，直接调用了GlobalExceptionHandler.handleOtherException方法
    @PostMapping("/test5")
    @ApiOperationLog(description = "测试参数检验异常")
    public Response test5(@RequestBody @Validated User user){
        return Response.success();
    }

}
