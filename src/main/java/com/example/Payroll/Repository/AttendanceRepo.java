package com.example.Payroll.Repository;

import com.example.Payroll.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    Attendance getByIdEmpAndDate(Long idEmp, LocalDate date);

    @Query("from Attendance where idEmp = :idEmp and date = CURRENT_DATE")
    Attendance getByIdEmpAndCurrentDate(Long idEmp);

    @Query("select count(status) from Attendance where idEmp = :idEmp and status = :status and MONTH(date) = MONTH(current_date)")
    Integer countStatusCurrentMonth(Long idEmp, int status);
}
