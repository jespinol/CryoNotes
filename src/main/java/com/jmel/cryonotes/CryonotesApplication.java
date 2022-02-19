package com.jmel.cryonotes;

import com.jmel.cryonotes.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SpringBootApplication
public class CryonotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryonotesApplication.class, args);
    }

    public static class CustomUserDetails implements UserDetails {

        private User user;

        public CustomUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getEmail();
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

        public String getFullName() {
            return user.getFirstName() + " " + user.getLastName();
        }

    }
}
