package une.revilla.project.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class StudentException {

    @ExceptionHandler(NoSuchElementException.class)
    public String error(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
