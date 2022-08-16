package com.program.EmployeeManagementSystem.Model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "employee")
public class EmployeeModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String employee_name;
    @Column
    private String employee_address;
    @Column
    private String employee_phoneno;
    @Column
    private String employee_gender;
    @Column
    private String employee_nationality;
    @Column
    private String employee_password;
    @Column
    private int orgid;
    @Column
    private String role;
    @Column
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "role_table", joinColumns = @JoinColumn(name = "employee", referencedColumnName = "orgid"), inverseJoinColumns = @JoinColumn(name = "organization", referencedColumnName = "id"))
    private Set<OrganizationModel> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getOrgname())).collect(Collectors.toList());
        return authorities;
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
