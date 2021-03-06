package une.revilla.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import une.revilla.project.dto.StudentDto;
import une.revilla.project.entity.Student;
import une.revilla.project.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(@Qualifier("studentService") StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String students(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new StudentDto());
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Students");
        model.addAttribute("logout", true);
        model.addAttribute("linkIndex", "/home");
        return "students";
    }

    @GetMapping("/students/{id}")
    public String getOneStudent(@RequestParam Long id, Model model) {
        studentService.findStudentById(id);
        return "redirect:/student/students";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") StudentDto student) {
        studentService.save(student);
        return "redirect:/student/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("myStudent", student);
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Update Students");
        model.addAttribute("logout", true);
        model.addAttribute("linkIndex", "/home");
        return "student-update";
    }

    @PostMapping("/update/process")
    public String updateProcess(@RequestParam Long id,
                                @ModelAttribute Student myStudent) {
        studentService.updateStudent(id, myStudent);
        return "redirect:/student/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/student/students";
    }
}
