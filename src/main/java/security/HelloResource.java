package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import controllers.AuthenticationRequest;

@Controller
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping (("/hello"))
        public String hello (){
            return "Hello World";
        }
        public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
        @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
        public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
                );    
            } catch(BadCredentialsException e){
                throw new Exception("Invalid Username and Password", e);
            }
            return null;
            
            final UserDetails userDetails = UserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            
                   
        }
    }
    

