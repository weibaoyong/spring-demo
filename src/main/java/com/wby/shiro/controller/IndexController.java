package com.wby.shiro.controller;

import com.wby.shiro.bean.User;
import com.wby.shiro.service.ResourceService;
import com.wby.shiro.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index(User user, Model model){
        Set<String> permissions = userService.findPermissions(user.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus",menus);
        return "index";
    }

    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("resourceList",resourceService.findAll());
        return "resource/list";
    }
}
