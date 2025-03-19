package com.pedroribeiro.helpaai.entities;


import com.pedroribeiro.helpaai.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "tb_usuario")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome",nullable = false,length = 150)
    private String name;

    @Column(name = "email",nullable = false,length = 100,unique = true)
    private String email;

    @Column(name = "senha",nullable = false,columnDefinition = "TEXT")
    private String password;

    @Column(name = "telefone",nullable = false,length = 20,unique = true)
    private String phone;

    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "id_setor")
    private Sector sector;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_OPERATOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
        if(this.role == UserRole.OPERATOR){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_OPERATOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENT")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
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
