package com.program.EmployeeManagementSystem.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.util.*;

@Data
@Entity

@Table(name = "employee")
public class EmployeeModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NotEmpty(message = "Name should not be empty")
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String employee_name;

    @NotEmpty(message = "Enter Address")
    @Column
    private String employee_address;
    @NotEmpty(message = "Phone number should not be empty")
    @Pattern(message="Phone number is not valid", regexp = "^[0-9]{10}$")
    @Column(name = "employee_phoneno")
    private String phoneNumber;
    @NotEmpty(message = "Gender should not be empty")
    @Column
    private String employee_gender;
    @NotEmpty(message = "Nationality should not be empty")
    @Column
    private String employee_nationality;

    @Column
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, message = "Password Length should be 8 or more than 8")
    private String employee_password;

    @NotNull(message = "orgId should not be empty")
    @Column(name = "orgid")
    private int orgId;

    @Column
    @NotEmpty(message = "Role should not be empty")
    @Pattern(message="Only characters are allowed", regexp = "^[a-zA-Z ]+$")
    private String role;

    @Column
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email is not valid", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*List<SimpleGrantedAuthority> simpleGrantedAuthorities=new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        return simpleGrantedAuthorities;*/
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.employee_password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
