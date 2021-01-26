package com.mryin.shyc.yc627_yjh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mryin.shyc.yc627_yjh.pojo.Admin;
import com.mryin.shyc.yc627_yjh.pojo.User;
import com.mryin.shyc.yc627_yjh.service.AdminService;
import com.mryin.shyc.yc627_yjh.service.UserService;
import com.mryin.shyc.yc627_yjh.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    @RequestMapping("Admin/toLogin")
    //去到登录页面
    public String toLogin(){
        return "public/login";
    }


    @RequestMapping("Admin/toWelcomeAdmin")
    //登陆成功后的欢迎页面
    public String toWelcomeAdmin(){
        return "Home/welcomeAdmin";
    }

    @RequestMapping("Admin/login")
    //登录
    public String login(@RequestParam("username") String username , @RequestParam("password") String password , Model model){
        Admin admin = adminService.queryAdminByUsername(username, password);
        System.out.println(admin);
        model.addAttribute("admin",admin);
        if(admin != null){
            return "Home/index";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "adminOperation/login";
        }
    }


    @RequestMapping("Admin/userInfo")
    //员工展示页面

    public String userList(Model model , HttpServletRequest request , @RequestParam(value="p" , defaultValue = "1") Integer page){
        int pageSize = 3 ;
        PageHelper.startPage(page,pageSize);
        List<User> users = userService.queryAllUser();
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        List<User> list = userPageInfo.getList();
        model.addAttribute("userList",list);

        StringBuffer requestURL = request.getRequestURL();
        String pageNav = Pager.getPageNav(page, pageSize, userPageInfo, requestURL);
        model.addAttribute("pageNav",pageNav);
        return "user/UserList";
    }


    @RequestMapping("Admin/toAddUser")
    //去到员工添加页面
    public String toAddUser(){
        return "user/UserAdd";
    }
    @RequestMapping("Admin/addUser")
    //员工添加页面
    public String addUser(User user){
        userService.addUser(user);
        System.out.println(user);
        return "redirect:/Admin/userInfo";
    }



    @RequestMapping("Admin/deleteUser/{username}")
    //员工删除
    public String deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return "redirect:/Admin/userInfo";
    }



    @GetMapping("Admin/toUpdateUser/{username}")
    public String toUpdateUser(@PathVariable("username")String username , Model model){
        User user = userService.queryAllUserByUsername(username);
        System.out.println(user);
        model.addAttribute("user",user);
        return "user/UserUpdate";
    }

    @RequestMapping("Admin/updateUser")
    public String updateUser(User user , HttpSession session){
        Object user1 = session.getAttribute("user");
        System.out.println(user1);
        userService.updateUser(user);
        return "redirect:/Admin/userInfo";
    }



    @RequestMapping("Admin/loginout")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/Admin/toLogin";
    }
}
