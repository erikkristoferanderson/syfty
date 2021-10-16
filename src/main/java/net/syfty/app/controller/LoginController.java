package net.syfty.app.controller;

import net.syfty.app.database.entity.User;
import net.syfty.app.database.repository.UserRepository;
import net.syfty.app.form.SignUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Login form
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/secret")
    public String secret() { return "secret"; }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView createUserPage() {
        ModelAndView result = new ModelAndView("signup");
        result.addObject("form", new SignUpForm());
        return result;
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createUserSubmit(@Valid SignUpForm form, BindingResult bindingResult) throws Exception {
        ModelAndView result = new ModelAndView("signup");

        // form validation
        result.addObject("form", form);


        if (bindingResult.hasErrors()) {

            List<String> errors = new ArrayList<String>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                logger.info(error.getField() + " = " + error.getDefaultMessage());
                errors.add(error.getDefaultMessage());
            }

            result.addObject("errorFields", bindingResult.getFieldErrors());
            result.addObject("errors", errors);
            result.addObject("usernameEntry", form.getEmail());
            return result;
        }

        // business logic
        User user = new User();

        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));


        userRepository.save(user);

        // goto the next page
        result = new ModelAndView("redirect:login");
        return result;
    }
}
