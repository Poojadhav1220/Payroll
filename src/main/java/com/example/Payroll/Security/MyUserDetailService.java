package com.example.Payroll.Security;


import com.example.Payroll.Entity.Employee;
import com.example.Payroll.Repository.EmpRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService
{
    private final EmpRepo EmpRepo;

    public MyUserDetailService(EmpRepo EmpRepo) {
        this.EmpRepo = EmpRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Employee emp = EmpRepo.getByEmail(email);

        System.out.println("emp = "  + emp.toString());

        if (emp == null) {
            throw new UsernameNotFoundException("Invalid User");
        }
        else
        {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            if(emp.isAdmin())
            {
                grantedAuthorities.add(
                        new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            else
            {
                grantedAuthorities.add(
                        new SimpleGrantedAuthority("ROLE_EMP"));
            }

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(emp.getEmail(), emp.getPassword(), grantedAuthorities);
        }
    }
}



