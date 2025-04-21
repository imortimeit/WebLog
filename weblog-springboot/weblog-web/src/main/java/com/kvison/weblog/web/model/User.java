package com.kvison.weblog.web.model;


import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author xueliang
 * Title: User</p>
 * Description:</p>
 * @date 2025/04/15
 */
@Data
public class User {
    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "age不能为空")
    @Min(value = 18,message = "age不能小于18")
    @Max(value = 100,message = "age不能大于100")
    private Integer age;

    @NotBlank(message = "email不能为空")
    @Email(message = "email格式不正确")
    private String email;
}
