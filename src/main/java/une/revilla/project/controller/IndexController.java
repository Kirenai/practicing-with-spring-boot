package une.revilla.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import une.revilla.project.dto.StudentDto;
import une.revilla.project.entity.Student;
import une.revilla.project.service.StudentService;

@Controller
@RequestMapping(path = "/")
public class IndexController {
    private final StudentService studentService;

    @Autowired
    public IndexController(@Qualifier("studentService") StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("valid", true);
        model.addAttribute("titleText", "Principal");
        model.addAttribute("linkIndex", "/");
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("loginStudent", new Student());
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Log In");
        model.addAttribute("linkIndex", "/");
        return "login";
    }

    @GetMapping(value = "/registration")
    public String signup(Model model) {
        model.addAttribute("student", new StudentDto());
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Sign Up");
        model.addAttribute("linkIndex", "/");
        return "signup";
    }

    @PostMapping(value = "/registration")
    public String signupProcess(@ModelAttribute("student") StudentDto studentDto) {
        studentService.save(studentDto);
        return "redirect:/login";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("logout", true);
        model.addAttribute("linkIndex", "/home");
        return "home";
    }

}
