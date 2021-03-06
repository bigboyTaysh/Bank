package pl.wolski.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**", "/webjars/**", "/", "/registrationForm.html", "/confirm", "/newPassword", "/passwordReset")
                .permitAll()//do powyższych zasobów ma mieć każdy
                .antMatchers( "/creditApplicationsList").hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest().authenticated();//pozostałe żądania mają być uwierzytelnione

        http
                .formLogin()//pozwól użytkownikom uwierzytelniać się poprzez formularz logowania
                .loginPage("/")//formularz dostępny jest pod URL
                .permitAll();//pozwól każdemu się zalogować.

        http
                .formLogin()
                .defaultSuccessUrl("/index", true);

        http.logout().permitAll();//pozwól każdemu się wylogować
    }



}

