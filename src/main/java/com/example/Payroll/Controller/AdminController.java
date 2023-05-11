package com.example.Payroll.Controller;


import com.example.Payroll.Entity.Attendance;
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

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController
{
    @Autowired
    EmpRepo empRepo;

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    HttpSession session;

    @Autowired
    FileUploader uploader;

    @GetMapping("/firstPage/")
    public String firstPage() {
        return "firstPage";
    }
    @GetMapping("/profile/")
    public String AdminProfile(Model model) {
        Long empID = Long.parseLong(session.getAttribute("idUser").toString());
        Employee employee=empRepo.getReferenceById(empID);
        model.addAttribute("emp", employee);
        return "profile";
    }

    @PostMapping("/profileSave/")
    public String saveProfile(Employee employee, Model model, MultipartFile file)
    {
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
        return "profile";
    }

    @GetMapping("/salary/")
    public String salary(Model model) {
        List<Employee> listEmp = empRepo.findAll();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("attendanceRepo", attendanceRepo);
        return "salary";
    }


    @GetMapping("/addEmp/")
    public String empadd(Model model){
        List<Employee> listEmp = empRepo.findByIsAdmin(false);
        model.addAttribute("listEmp", listEmp);
        return "addEmp";
    }


    @PostMapping("/empSave/")
    public String addEmp(Model model, Employee employee)
    {
        System.out.println(employee.toString());
        empRepo.save(employee);
        List<Employee> listEmp = empRepo.findAll();

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("msg", "Employee Add Successfully");
        return "addEmp";
    }


    @GetMapping("/empDelete/{empID}/")
    public String deleteEmp(Model model, @PathVariable Long empID)
    {
        try
        {
            empRepo.deleteById(empID);
            model.addAttribute("status", 2);
        }
        catch(Exception ex)
        {
            model.addAttribute("status", 0);
        }


        empRepo.deleteById(empID);
        List<Employee> listEmp = empRepo.findAll();

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("msg", "Record deleted successfully");
        return "addEmp";
    }

    @GetMapping("/empUpdate/{empID}")
    public String viewEmp(Model model, Employee employee){
        empRepo.save(employee);

        model.addAttribute("employee", employee);
        return "addEmp";
    }

    @GetMapping("/attendance/")
    public String attendance(Model model){
        List<Employee> listEmp = empRepo.findAll();

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("attendanceRepo", attendanceRepo);
        return "attendance";
    }

    @GetMapping("/attendance/mark/{idEmp}/{status}/")
    @ResponseBody
    public int markAttendance(@PathVariable long idEmp, @PathVariable int status)
    {
        int result = 0;

        Attendance attendance = attendanceRepo.getByIdEmpAndCurrentDate(idEmp);

        if(attendance == null)
        {
            attendanceRepo.save(new Attendance(idEmp, status));
        }
        else
        {
            attendance.setStatus(status);
            attendanceRepo.save(attendance);
        }

        result = 1;

        return result;
    }


}
