package uz.yt.springdata.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import uz.yt.springdata.service.UserDetailsService;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource);
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public JdbcUserDetailsManager muhammadali(){
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager();
        jdbc.setDataSource(dataSource);
        jdbc.setCreateUserSql("insert into users(firstname, lastname, phonenumber, account, password, username, phone_number, enabled) " +
                "values(?,?,?,?,?,?,?,?)");
        return jdbc;
    }

    //    @Bean
//    public InMemoryUserDetailsManager muhammadali(){
//        UserDetails userDetails = User.withUsername("user")
//                .password(passwordEncoder().encode("123"))
////                .roles("USER")
//                .authorities(GUEST.getPermissions())
//                .build();
//
//        UserDetails userDetails2 = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
////                .roles("USER")
//                .authorities(ADMIN.getPermissions())
//                .build();
//
//
//        return new InMemoryUserDetailsManager(userDetails,  userDetails2);
//    }
}
