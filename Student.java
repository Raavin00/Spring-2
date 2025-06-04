package com.example.studentapp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
 
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
 
    @NotBlank(message = "Name is required")
    private String name;
 
    @Min(value = 5, message = "Age must be at least 5")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;
 
    @NotBlank(message = "Class is required")
    private String studentClass;
 
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
 
    @NotBlank(message = "Address is required")
    private String address;
 
    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
