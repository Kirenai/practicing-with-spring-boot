package une.revilla.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import une.revilla.project.entity.Student;
import une.revilla.project.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(@Qualifier(value = "studentService")
                                     StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String students(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Students");
        return "students";
    }

    @GetMapping("/students/{id}")
    public String getOneStudent(@RequestParam(name = "id") Long id,
                                Model model) {
        studentService.findStudentById(id);
        return "redirect:/student/students";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        if (student.getId() == null) {
            return "redirect:/student/students";
        }
        try {
            studentService.saveStudent(student);
        } catch (Exception e) {
            return "redirect:/student/students";
        }
        return "redirect:/student/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable(value = "id") Long id,
                                Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("myStudent", student);
        model.addAttribute("valid", false);
        model.addAttribute("titleText", "Update Students");
        return "student-update";
    }

    @PostMapping("/update/process")
    public String updateProcess(@RequestParam(value = "id") Long id,
                                @ModelAttribute Student myStudent) {
        studentService.updateStudent(myStudent, id);
        return "redirect:/student/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/student/students";
    }
}
