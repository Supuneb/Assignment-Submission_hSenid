package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AuthenticationRequest;
import controllers.AuthenticationResponse;

@RestController
@Controller
public class HelloResource {
    
    @GetMapping("/") 
    public String home(){
        return ("<h1>Welcome<h1>");
    }

    @GetMapping("/user")
    public String user(){
        return("<h1>Welcome User<h1>");
    }
    @GetMapping("/admin")
    public String admin(){
        return("<h1>Welcome Admin<h1>");
    }
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
            
            
            final UserDetails userDetails = UserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
            
                   
        }
    }
    

