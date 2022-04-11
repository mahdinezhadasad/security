package com.example.security.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.security.security.ApplicationUserPermission.*;
import static com.google.common.collect.Sets.newHashSet;

public enum ApplicationUserRole {

    STUDENT(newHashSet()),
    ADMIN(newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(newHashSet(COURSE_READ,STUDENT_READ));


    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {

        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions(){


        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){

          Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                  .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                  .collect(Collectors.toSet());

          permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

          return permissions;



    }
}
