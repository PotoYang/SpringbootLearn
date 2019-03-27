package com.potoyang.learn.blog.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potoyang.learn.blog.user.model.User;
import com.potoyang.learn.blog.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/21 16:12
 * Modified:
 * Description:
 */
@RestController
@Slf4j
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("page/{pageNum}")
    public ResponseEntity<List<User>> findAllUserPage(@PathVariable("pageNum") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "123");
        return new ResponseEntity<>(userService.page(new Page<>(pageNum, pageSize), wrapper).getRecords(), HttpStatus.OK);
//        return new ResponseEntity<>(userService.findAllUserPage(new Page<>(pageNum, pageSize)), HttpStatus.OK);
    }
}
