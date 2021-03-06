package com.example.demo.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.service.TokenAuthenticationFilter;
import com.example.demo.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;
	
	@Autowired
	TokenAuthenticationFilter tokenAuthenticationFilter;
	
	 @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        // Get AuthenticationManager bean
	        return super.authenticationManagerBean();
	    }
	
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
		    .passwordEncoder(passwordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//	        .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
//	        .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
//	        .authorizeRequests()
//        	.antMatchers("/auth/**").permitAll()
//        	.antMatchers(SWAGGER_WHITELIST).permitAll()
//        	//.antMatchers("/organization/**").hasAnyRole("ADMIN")
//        	.anyRequest().authenticated()
//        	//.anyRequest().permitAll();
//	        .and()
//	        .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class);
//
        http.csrf().disable();
        
        
//		// We don't need CSRF for this
		 http
//         .cors() // Ng??n ch???n request t??? m???t domain kh??c
//             .and()
         .authorizeRequests()
             .antMatchers("/api/**").permitAll() // Cho ph??p t???t c??? m???i ng?????i truy c???p v??o ?????a ch??? n??y
             .anyRequest().authenticated(); // T???t c??? c??c request kh??c ?????u c???n ph???i x??c th???c m???i ???????c truy c???p

		// Add a filter to validate the tokens with every request
        http.addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class);

    }
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:3000");
			}
		};
	}
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8087/api/reacts"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin",
//                "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
