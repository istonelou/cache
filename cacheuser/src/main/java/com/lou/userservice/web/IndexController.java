/**
 * 
 */
package com.lou.userservice.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lou.userservice.entity.UserInfo;
import com.lou.userservice.service.IUserService;
import com.lou.userservice.util.AESUtil;

/**
 * @author louxiaobo
 *
 */
@Controller
public class IndexController {

	@Autowired
    private IUserService userService;
	
	@Value("${aes.key}")
	String aesKey;
	
    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
    
    @RequestMapping("/login")
    String login(Model model) {
        model.addAttribute("user", new UserInfo());
        return "login";
    }
    

    @RequestMapping("/register")
    String register(Model model) {
        model.addAttribute("user", new UserInfo());
        return "register";
    }
    
    @RequestMapping("/userlist")
    String userlist(Model model) {
    	userService.findAll("username");
        model.addAttribute("userinfo", userService.findAll("username"));
        return "userlist";
    }
    
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    String userLogin(UserInfo user, Model model,HttpServletRequest req) throws Exception {
    	UserInfo qUserInfo = userService.findUserByUsername(user.getUsername());

        if (qUserInfo!=null) {
        	
        	String password = qUserInfo.getPassword();
        	if(password !=null)
        	{
        		password = AESUtil.decrypt(qUserInfo.getPassword(), aesKey);
        	}
        	if(password.equals(user.getPassword()))
        	{
        		req.getSession().setAttribute("logInStatus","true");
        		model.addAttribute("name", user.getName());
        		model.addAttribute("password", user.getPassword());
            return "welcome";
        	}
        } 
        
        model.addAttribute("user", new UserInfo());
        return "login";

    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    String userRegister(UserInfo user, Model model,HttpServletRequest req) {
    	
    	int addresult = userService.insertUser(user.getUsername(),user.getPassword(),user.getName());
    	if(addresult>0)
    	{
    		req.getSession().setAttribute("registerStatus","true");
    		model.addAttribute("user", new UserInfo());
    		return "login";
    	}
    	
    	model.addAttribute("user", new UserInfo());
    	return "register";
    }

}
