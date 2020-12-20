package une.revilla.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import une.revilla.project.entity.Student;
import une.revilla.project.service.StudentService;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    private final StudentService studentService;

    @Autowired
    public IndexController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("valid", true);
        model.addAttribute("titleText", "Principal");
        return "index";
    }
    
    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("loginStudent", new Student());
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Log In");
        return "login";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }
    
    @GetMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Sign Up");
        return "signup";
    }

    @PostMapping(value = "/login-process")
    public String loginProcess(@ModelAttribute Student student) {
        if (studentService.validLogin(student)) return "redirect:/home";
        else return "redirect:/login";
    }

    @PostMapping(value = "/signup-process")
    public String signupProcess(@ModelAttribute Student newStudent) {
        try {
            studentService.saveStudent(newStudent);
        } catch (Exception e) {
            return "redirect:/signup";
        }
        return "redirect:/login";
    }
}
