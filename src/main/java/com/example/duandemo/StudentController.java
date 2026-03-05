package com.example.duandemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * GET /
     * Serves the main index page
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    /**
     * GET /students
     * Redirects to the root view so that we have a single interactive page
     */
    @GetMapping("/students")
    public String getAllStudents() {
        // simply redirect to root where the interactive page lives
        return "redirect:/";
    }

    /**
     * GET /students/search
     * Optional MVC search route (for direct URL requests)
     */
    @GetMapping("/students/search")
    public String searchStudents(@RequestParam(name = "name", required = false) String name,
                                 Model model) {
        if (name == null || name.isEmpty()) {
            model.addAttribute("students", studentService.getAllStudents());
        } else {
            model.addAttribute("students", studentService.searchStudents(name));
        }
        return "students"; // this template is now rarely used because /students redirects to /
    }

    /* MVC form actions – available if someone posts to these URLs */

    @PostMapping("/students")
    public String createStudentMvc(Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudentMvc(@PathVariable Integer id, Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @PostMapping("/students/delete/{id}")
    public String deleteStudentMvc(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}