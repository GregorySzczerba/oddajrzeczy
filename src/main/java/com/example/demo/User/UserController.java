package com.example.demo.User;

import com.example.demo.ConfirmationToken.ConfirmationToken;
import com.example.demo.ConfirmationToken.ConfirmationTokenRepository;
import com.example.demo.foundation.Foundation;
import com.example.demo.foundation.FoundationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Transactional
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String logged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().toString().equals("[ADMIN]")) {

            return "redirect:adminpanel" ;
        }
        return "redirect:userpanel";
    }

    @RequestMapping(value = "/userpanel", method = RequestMethod.GET)
    public ModelAndView userpanel() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("userpanel");
        return modelAndView;
    }

    @RequestMapping(value="/adminpanel", method = RequestMethod.GET)
    public ModelAndView adminpanel(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Pageable pageableAdmins = new PageRequest(0, 5);
        Pageable pageableUsers = new PageRequest(0, 5);


        List<User> admins = userService.selectAdmins(user.getId(), 1, pageableAdmins);
        List<User> users = userService.selectUsers(user.getId(), 3, pageableUsers );
        List<Foundation> foundations = foundationService.selectFoundations();

        modelAndView.addObject("admins", admins);
        modelAndView.addObject("users", users);
        modelAndView.addObject("organisations", foundations);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.setViewName("adminpanel");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Taki email już istnieje w bazie.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
                        userService.saveUser(user);

            try {
                notificationService.sendNotification(user);
            } catch (MailException e) {
                logger.info("Error while sending email " + e.getMessage());
            }
            modelAndView.addObject("successMessage", "Użytkownik został pomyślnie zarejestrowany");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value="/confirm-account/{token}", method= RequestMethod.GET)
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @PathVariable String token) {

    ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if(token != null)
        {
            User user = userRepository.findByEmail(confirmationToken.getUser().getEmail());
            System.out.println(confirmationToken.getUser().getEmail());
            user.setActive(1);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");


        }

        return modelAndView;
    }

    @RequestMapping(value="/addadmin", method = RequestMethod.GET)
    public ModelAndView createAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addadmin");
        return modelAndView;
    }

    @RequestMapping(value = "/addadmin", method = RequestMethod.POST)
    public ModelAndView createNewAdmin(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Taki email już istnieje w bazie.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addadmin");
        } else {
            userService.saveAdmin(user);

            try {
                notificationService.sendNotification(user);
            } catch (MailException e) {
                logger.info("Error while sending email " + e.getMessage());
            }
            modelAndView.addObject("successMessage", "Użytkownik został pomyślnie zarejestrowany");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("redirect:adminpanel");

        }
        return modelAndView;
    }

    @GetMapping("/deleteuser/{id}")
    public String delete(@PathVariable int id) {
        User user = userRepository.findById(id);
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByUserId(id);
        confirmationTokenRepository.delete(confirmationToken);
        userRepository.delete(user);
        return "redirect:/admins";
    }

    @GetMapping("/edituser/{id}")
    public ModelAndView editUser(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userRepository.findById(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("edituser");
        return modelAndView;
    }

    @PostMapping("/edituser")
    public String edituser(User user, BindingResult bindingResult) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return "redirect:/adminpanel";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageableAdmins = new PageRequest(0, 5);
        User user = userService.findUserByEmail(auth.getName());
        List<User> admins = userService.selectAdmins(user.getId(), 1, pageableAdmins);
        model.addAttribute("admins", admins);
        return "admins";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageableAdmins = new PageRequest(0, 5);
        User user = userService.findUserByEmail(auth.getName());
        List<User> users = userService.selectUsers(user.getId(), 1, pageableAdmins);
        model.addAttribute("users", users);
        return "users";
    }

}

