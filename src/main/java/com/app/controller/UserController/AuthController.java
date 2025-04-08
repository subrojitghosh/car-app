package com.app.controller.UserController;

import com.app.entity.User;
import com.app.payload.Athintication.JWTTokenDto;
import com.app.payload.Athintication.LoginDto;
import com.app.payload.Athintication.Userdto;
import com.app.payload.CarsDTO.CarDto;
import com.app.repository.AthinticationRepository.UserRepository;
import com.app.service.UserServices.AuthService;
import com.app.service.UserServices.JWTService;
import com.app.service.UserServices.OTPService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService as;
    private OTPService otpService;
    private UserRepository userRepository;
    private JWTService jwtService;

    public AuthController(AuthService as, UserRepository us, OTPService otpService, UserRepository userRepository, JWTService jwtService) {
        this.as = as;

        this.otpService = otpService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Userdto> createUser(@RequestBody Userdto user) {

        Userdto adduser = as.adduser(user);

        return new ResponseEntity<>(adduser, HttpStatus.CREATED);
    }

    @PostMapping("/content-manager-signup")
    public ResponseEntity<Userdto> createContentManagerAccount(@RequestBody Userdto user) {

        Userdto addu = as.addContentManager(user);

        return new ResponseEntity<>(addu, HttpStatus.CREATED);
    }

    @PostMapping("/blog-manager-signup")
    public ResponseEntity<Userdto> createBlogManagerAccount(@RequestBody Userdto user) {

        Userdto us = as.addBlogManager(user);

        return new ResponseEntity<>(us, HttpStatus.CREATED);
    }
    @PostMapping("/usersignin")
    public ResponseEntity<?> userSignIn(@RequestBody LoginDto dto){
        String jwtToken = as.verfyLogin(dto);
        if (jwtToken!=null){
            JWTTokenDto tokenDto=new JWTTokenDto();
            tokenDto.setToken(jwtToken);
            tokenDto.setTokenType("JWT");
            return new ResponseEntity<> (tokenDto,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid token",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login-otp")
    public ResponseEntity<String> generateOtp(String mobile){
        Optional<User> opuser = userRepository.findByMobile(mobile);
        if(opuser.isPresent()) {
            String otp = otpService.generateOTP(mobile);
            return new ResponseEntity<>(otp + " " + mobile,HttpStatus.OK) ;
        }
        return new ResponseEntity<>("User not found ",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/validate-otp")
    public String ValidateOtp(
            @RequestParam String mobile,
            @RequestParam String otp
    ){
        boolean status = otpService.ValidateOTP(mobile, otp);
        if(status){
            Optional<User> opu = userRepository.findByMobile(mobile);
            if(opu.isPresent()){
                String jwtToken = jwtService.generateToken(opu.get().getUsername());
                return jwtToken;
            }
        }
        return "Invalid OTP";
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Userdto>> getAll(
            @RequestParam(defaultValue = "0",required = false)int pageNo,
            @RequestParam(defaultValue = "10",required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false)String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ) {
        List<Userdto> allUser = as.getAll(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allUser , HttpStatus.OK);
    }

    @DeleteMapping("delete-id/{id}")
    public ResponseEntity<String>  deleteUsers(@PathVariable Long id) {
        as.deleteByCar(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<Userdto> updateCar(@PathVariable Long id, @RequestBody Userdto userdto){
       as.updateUser(id,userdto);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }
}
