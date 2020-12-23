package une.revilla.project.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class StudentException {

    @ExceptionHandler(NoSuchElementException.class)
    public String error(NoSuchElementException e, Model model) {
        System.out.println(e);
        model.addAttribute("error", e.getMessage());
        model.addAttribute("valid", false);
        model.addAttribute("anchor", "/student/students");
        return "error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String emailDuplicate(DataIntegrityViolationException e, Model model) {
        model.addAttribute("error", "This email is already registered");
        model.addAttribute("valid", false);
        model.addAttribute("anchor", "/registration");
        return "error";
    }

}
