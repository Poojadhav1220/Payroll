package com.example.Payroll.Controller;


import com.example.Payroll.Entity.Employee;
import com.example.Payroll.Helper.FileUploader;
import com.example.Payroll.Repository.AttendanceRepo;
import com.example.Payroll.Repository.EmpRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/emp/")
public class EmpController {
    @Autowired
    EmpRepo empRepo;

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    HttpSession session;

    @Autowired
    FileUploader uploader;

     @GetMapping("/firstPage/")
     public String firstPage()
     {

        return "firstPageEmp";
     }

     @GetMapping("/salaryEmp/")
     public String empSalary(Model model) {
         Long empID = Long.parseLong(session.getAttribute("idUser").toString());
         Employee employee=empRepo.getReferenceById(empID);
         model.addAttribute("emp", employee);
         model.addAttribute("attendanceRepo", attendanceRepo);
         return "salaryEmp";
     }

    @GetMapping("/profileEmp/")
    public String empProfile(Model model) {
        Long empID = Long.parseLong(session.getAttribute("idUser").toString());
        Employee employee=empRepo.getReferenceById(empID);
        model.addAttribute("emp", employee);
        return "profileEmp";
    }


    @PostMapping("/profileSave/")
     public String saveInfo(Employee employee, Model model,  MultipartFile file){
        System.out.println("employee = " + employee.toString());

        Employee empExisting = empRepo.getReferenceById(employee.getId());

        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        employee.setExe(extension);
        employee.setAdmin(empExisting.isAdmin());
        employee.setPassword(empExisting.getPassword());

        String fileNameNew = employee.getId() + "." + extension;
        uploader.uploadFile(file, fileNameNew);
        empRepo.save(employee);
        model.addAttribute("emp", employee);
        return "profileEmp";
     }












}
