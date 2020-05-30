package cn.com.liandisys.shopmall.controller;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.com.liandisys.shopmall.common.Conts;
import cn.com.liandisys.shopmall.entity.Users;
import cn.com.liandisys.shopmall.enumerat.LoginErrorEnum;
import cn.com.liandisys.shopmall.enumerat.UserTypeEnum;
import cn.com.liandisys.shopmall.service.UserService;
import cn.com.liandisys.shopmall.util.MD5Util;

@Controller
public class UserController extends BaseController{
  
  @Autowired
  private UserService userService;

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(Conts.SESSION_USER_KEY);
        return Conts.PATH_INDEX;
    }

    @GetMapping({"/login", "login.html"})
    public String loginPage() {
      return Conts.PATH_LOGIN;
    }

    @PostMapping("/login")
    public String login(@RequestParam("uname") String uname,
                        @RequestParam("verifyCode") String verifyCode,
                        @RequestParam("upwd") String upwd,
                        HttpServletRequest request , HttpSession httpSession) {
        if (StringUtils.isEmpty(uname)) {
          //用户名为空
          request.setAttribute("errorType", LoginErrorEnum.NULLUNAME.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        if (StringUtils.isEmpty(upwd)) {
          //密码为空
          request.setAttribute("errorType", LoginErrorEnum.NULLUPWD.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        if (StringUtils.isEmpty(verifyCode)) {
          //验证码为空
          request.setAttribute("errorType", LoginErrorEnum.NULLVCODE.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        Object kaptchaObj = httpSession.getAttribute(Conts.MALL_VERIFY_CODE_KEY);
        String kaptchaCode = Objects.isNull(kaptchaObj) ? Conts.STRING_EMPTY : kaptchaObj.toString();
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
          //验证码错误
          request.setAttribute("errorType", LoginErrorEnum.VCODE.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        Users user = userService.getUserByUname(uname);
        if(Objects.isNull(user)) {
          //用户不存在
          request.setAttribute("errorType", LoginErrorEnum.ERRORUSER.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        if(user.getUtype() == UserTypeEnum.ADMIN.getEnumValue()) {
          //后台管理用户
          request.setAttribute("errorType", LoginErrorEnum.ADMINUSER.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        //验证密码
        String inputUpwd = MD5Util.MD5Encode(upwd, Conts.CHARSET_UTF_8);
        if(!inputUpwd.equals(user.getUpwd())) {
          //密码不正确
          request.setAttribute("errorType", LoginErrorEnum.UPWD.getEnumValue());
          return Conts.PATH_LOGIN;
        }
        super.setCurUser(httpSession, user);
        return "redirect:/index";
    }

}
