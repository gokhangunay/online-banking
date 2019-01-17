package com.banking.user.controller;

import com.banking.user.dao.RoleDao;
import com.banking.user.domain.PrimaryAccount;
import com.banking.user.domain.SavingsAccount;
import com.banking.user.domain.User;
import com.banking.user.domain.security.Role;
import com.banking.user.domain.security.UserRole;
import com.banking.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired()
    private UserService userService;

    @Autowired
    private RoleDao roleDao;

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


        if(userService.checkUserExists(user.getUsername(),user.getEmail())){
            if(userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("usernameExist",true);
            }

            if(userService.checkUserEmailExists(user.getEmail())){
                model.addAttribute("userEmailExist",true);
            }

            return "signup";

        }else{

            Set<UserRole> userRoles = new HashSet<>();
            Role role = roleDao.findByName("USER");
            userRoles.add(new UserRole(user, role));

            userService.createUser(user, userRoles);
            // userService.save(user); artık role ve password eklendiği için burası commente alındı!

            return "redirect:/";
        }
    }

    @RequestMapping("/user")
    public String user(Principal principal, Model model){

        User user = userService.findByUsername(principal.getName());

        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "user";
    }

}
