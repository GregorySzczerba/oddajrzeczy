package com.example.demo.User;

import com.example.demo.ConfirmationToken.ConfirmationToken;
import com.example.demo.ConfirmationToken.ConfirmationTokenRepository;
import com.example.demo.Gifts.GiftsService;
import com.example.demo.foundation.Foundation;
import com.example.demo.foundation.FoundationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private GiftsService giftsService;

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
        modelAndView.addObject("user", user);
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.addObject("gifts", giftsService.userGifts(user.getId()) );
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


        Page<User> admins = userService.selectAdmins(user.getId(), 1, pageableAdmins);
        Page<User> users = userService.selectUsers(user.getId(), 3, pageableUsers );
        List<Foundation> foundations = foundationService.selectFoundations();

        modelAndView.addObject("admins", admins);
        modelAndView.addObject("users", users);
        modelAndView.addObject("foundations", foundations);
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
        //ConfirmationToken confirmationToken = confirmationTokenRepository.findByUserId(id);
        userRepository.delete(user);
        return "redirect:/adminpanel";
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

    @GetMapping("/editLoggedUser")
    public String editLoggedUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user", user);
        return "editLoggedUser";
    }

    @PostMapping("/editLoggedUser")
    public String editLoggedUser(User user, BindingResult bindingResult) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setActive(1);
        userRepository.save(user);
        return "redirect:/userpanel";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageableAdmins = new PageRequest(0, 5);
        User user = userService.findUserByEmail(auth.getName());
        Page<User> admins = userService.selectAdmins(user.getId(), 1, pageableAdmins);
        model.addAttribute("admins", admins);
        return "admins";
    }

    @GetMapping("/users/{page}")
    public String users(@PathVariable("page") int page, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int size = 5;
        User user = userService.findUserByEmail(auth.getName());
        Page<User> users = userService.selectUsers(user.getId(), 3, new PageRequest(page - 1, size));
        int totalPages = users.getTotalPages();
        int currentPage = users.getNumber();
        List<User> userList = users.getContent();
        model.addAttribute("users", userList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        return "users";
    }

    @GetMapping("/blockuser/{id}")
    public String blockuser(@PathVariable  int id) {
        User user = userRepository.findById(id);
        if (user.getActive() == 0) {
            user.setActive(1);
        } else {
            user.setActive(0);
        }
        userRepository.save(user);
        return "redirect:/users";
    }
}

