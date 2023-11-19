package com.zust.controller;


import com.zust.domain.Entities.User;
import com.zust.domain.DTO.UserDTO;
import com.zust.domain.VO.UserVO;
import com.zust.service.UserService;
import com.zust.utils.R;
import com.zust.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 登录功能
     * @param userDTO
     * @return
     */
    @ApiOperation("登录")
    @PostMapping
    public R login(@RequestBody UserDTO userDTO){
        //登录
        User user = User.builder().id(userDTO.getId()).password(userDTO.getPassword()).build();
        User user1 = userService.login(user);
        Integer id = user1.getId();
        String password = user1.getPassword();
        //获取令牌
        String token = TokenUtils.createToken(id.toString(), password);
        //封装返回对象
        UserVO userVO = UserVO.builder()
                .id(id)
                .name(user1.getName())
                .password(password)
                .token(token)
                .build();
        return new R(true,userVO);

    }
}
