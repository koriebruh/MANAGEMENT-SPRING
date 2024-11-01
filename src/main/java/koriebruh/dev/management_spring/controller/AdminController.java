package koriebruh.dev.management_spring.controller;

import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.model.LoginRequest;
import koriebruh.dev.management_spring.model.LoginResponse;
import koriebruh.dev.management_spring.model.RegisterRequest;
import koriebruh.dev.management_spring.model.RegisterResponse;
import koriebruh.dev.management_spring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admins")
public class AdminController {

    @Autowired
     private AdminService adminService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<RegisterResponse> addAdmin(@Validated @RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = adminService.registerAdmin(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

//    @PostMapping
//    public LoginResponse login(@Validated @RequestBody LoginRequest loginRequest) {
//
//
//        return new LoginResponse(
//
//        )
//    }
}
