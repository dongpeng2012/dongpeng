package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller

public class LoginController {
    @Autowired
    private UserService userService;
    //登入系统并把信息放入userSession  login.jsp
    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String login(String userCode, String userPassword, HttpSession session, HttpServletRequest request){
        User user = userService.login(userCode, userPassword);
        if (user != null) {

            System.out.println("登陆成功");
            session.setAttribute("userSession",user);
            return "frame";
        }else {
            request.setAttribute("error","用户名或密码错误");
            return "forward:/login.jsp";
        }
    }
    //退出系统session  userlist.jsp
    @RequestMapping("/jsp/logout.do")
    public String logout(HttpSession session){
        session.invalidate();
        return "forward:/login.jsp";
    }
}
