package koriebruh.dev.management_spring.service;

import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.model.LoginRequest;
import koriebruh.dev.management_spring.model.LoginResponse;
import koriebruh.dev.management_spring.model.RegisterRequest;
import koriebruh.dev.management_spring.model.RegisterResponse;
import koriebruh.dev.management_spring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterResponse registerAdmin(RegisterRequest registerRequest) {

        Admin admin = new Admin();
        admin.setUsername(registerRequest.getUsername());
        admin.setPassword(registerRequest.getPassword());
        admin.setEmail(registerRequest.getEmail());

        adminRepository.save(admin);

        return new RegisterResponse("success! registration new admin");
    }

    @Transactional
    public void deleteAdminById(int id) {
        if (adminRepository.existsById(id)){
            adminRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Admin with id "+ id +" is not found");
        }
    }
//
//    public LoginResponse loginAdmin(LoginRequest loginRequest) {
//        Admin admin = adminRepository.findByUsername(loginRequest.getUsername());
//
//        if (admin != null && passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
//            String token = generateToken(admin);
//            return new LoginResponse(admin.getId(), token, "Login successful!");
//        } else{
//            throw new IllegalArgumentException("Admin with name "+ loginRequest.getUsername() + " is not found");
//        }
//    }
//
//
//    //STATIC DULU BG JWT BELUM DI BIKIN
//    public String generateToken(Admin admin) {
//        // Contoh logika pembuatan token
//        return "generated-jwt-token";
//    }

}
