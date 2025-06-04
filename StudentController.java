package com.example.studentapp.controller;
import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
 
@Controller
@RequestMapping("/students")
public class StudentController {
 
    @Autowired
    private StudentRepository studentRepository;
 
    @GetMapping
    public String listStudents(Model model,
                               @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Student> studentPage = studentRepository.findByNameContainingIgnoreCaseOrStudentClassContainingIgnoreCase(keyword, keyword, pageable);
        model.addAttribute("students", studentPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "students/list";
    }
 
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }
 
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "students/form";
        }
        studentRepository.save(student);
        return "redirect:/students";
    }
 
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("student", student);
        return "students/form";
    }
 
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}
