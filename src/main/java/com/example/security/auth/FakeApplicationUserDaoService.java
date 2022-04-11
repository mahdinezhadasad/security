package com.example.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.security.security.ApplicationUserRole.*;


@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {


    private final PasswordEncoder passwordEncoder;
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder){

        this.passwordEncoder = passwordEncoder;

    }

    private List<ApplicationUser> getApplicationUsers(){


        List<ApplicationUser> applicationUsers = Lists.newArrayList(

                new ApplicationUser(

                        STUDENT.getGrantedAuthorities(),
                        "annasmith",
                        passwordEncoder.encode("password"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "linda",
                        true,
                        true,
                        true,
                        true

                ),
        new ApplicationUser(
                ADMINTRAINEE.getGrantedAuthorities(),
                passwordEncoder.encode("password"),
                "Tom",
                true,
                true,
                true,
                true
        )
        );
            return applicationUsers;

    }
}
