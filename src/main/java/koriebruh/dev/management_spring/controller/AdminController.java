package koriebruh.dev.management_spring.controller;

import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.model.LoginRequest;
import koriebruh.dev.management_spring.model.LoginResponse;
import koriebruh.dev.management_spring.model.RegisterRequest;
import koriebruh.dev.management_spring.model.RegisterResponse;
import koriebruh.dev.management_spring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping(path = "/login",
//            consumes = "application/json"
//    )
//    public ResponseEntity<LoginResponse> loginAdmin(@Validated @RequestBody LoginRequest loginRequest) {
//        LoginResponse loginResponse = adminService.loginAdmin(loginRequest);
//        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
//    }
//
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
        try {
            adminService.deleteAdminById(id);
            return new ResponseEntity<>("Admin with id " + id + " deleted successfully.", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
