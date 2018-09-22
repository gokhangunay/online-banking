package com.banking.user.controller;

import com.banking.user.domain.User;
import com.banking.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model){


        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user")User user, Model model){


        if(userService.checkUserExists(user.getUserName(),user.getEmail())){
            if(userService.checkUserNameExists(user.getUserName())){
                model.addAttribute("userNameExist",true);
            }

            if(userService.checkUserEmailExists(user.getEmail())){
                model.addAttribute("userEmailExist",true);
            }
        }else{
            // userService.createUser(user);
            userService.save(user);

        }

        return "redirect:/";




    }

}
