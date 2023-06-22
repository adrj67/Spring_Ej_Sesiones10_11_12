package com.ejercicios.sesion10_11_12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 1- paginas permitidas sin password
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hola").permitAll()
                .antMatchers("/bootstrap").permitAll()
               
                .antMatchers("/api/**").hasRole("USER") // rol necesario para ver esta pagina
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }

    // 3- Firewall para evitar hackeos mediante el envio de caracteres espaciales 
    // como punto y coma,etc. se deja por defecto. En caso de permitir algun caracter
    // se especifica cuales
    @Bean
    public HttpFirewall looseHttpFirewall(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowBackSlash(true); // permite los BackSlash
        firewall.setAllowSemicolon(true); // permite los ; en la url
        firewall.setAllowUrlEncodedSlash(true); // permite los EncodedSlash
        // ...
        return firewall;
    }
//
    
    // 2- Creacion de usuarios y contraseñas
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder()) // llama al metodo para codificar contraseñas
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
    }

    // 4- para codificar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // Expose the UserDetailsService as a Bean
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
}
