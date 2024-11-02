package koriebruh.dev.management_spring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.model.LoginRequest;
import koriebruh.dev.management_spring.model.LoginResponse;
import koriebruh.dev.management_spring.model.RegisterRequest;
import koriebruh.dev.management_spring.model.RegisterResponse;
import koriebruh.dev.management_spring.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/auth")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
     private AdminService adminService;

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<RegisterResponse> addAdmin(@Validated @RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = adminService.registerAdmin(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }


    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<LoginResponse> loginAdmin(@Validated @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = adminService.loginAdmin(loginRequest);

        // Buat cookie untuk menyimpan token
        Cookie cookie = new Cookie("token", loginResponse.getToken());
        cookie.setHttpOnly(true); // Mengatur cookie hanya dapat diakses melalui HTTP
        cookie.setPath("/"); // Set path untuk cookie
        cookie.setMaxAge(60 * 60 * 10); // Set cookie berlaku selama 10 jam
        response.addCookie(cookie); // Menambahkan cookie ke respons


        return ResponseEntity.ok(loginResponse);
    }

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
