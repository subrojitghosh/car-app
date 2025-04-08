package com.app.config;

import com.app.entity.User;
import com.app.repository.AthinticationRepository.UserRepository;
import com.app.service.UserServices.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private UserRepository userRepository;


    public JWTFilter(JWTService jwtService, UserRepository userRepository ) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
//        System.out.println(token);
       if(token!=null && token.startsWith("Bearer ")){
           String jwtToken = token.substring(8,token.length()-1);
//           System.out.println(jwtToken);
           String username = jwtService.getUsername(jwtToken);

           Optional<User> opUser =userRepository.findByUsername(username);
           if(opUser.isPresent()){
               User user = opUser.get();

               UsernamePasswordAuthenticationToken authenticationToken=
                       new UsernamePasswordAuthenticationToken(user,
                               null,
                               Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
               authenticationToken.setDetails(new WebAuthenticationDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }
       filterChain.doFilter(request,response);
    }
}
