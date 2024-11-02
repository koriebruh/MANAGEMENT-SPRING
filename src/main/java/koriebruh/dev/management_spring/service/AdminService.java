package koriebruh.dev.management_spring.service;

import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.model.LoginRequest;
import koriebruh.dev.management_spring.model.LoginResponse;
import koriebruh.dev.management_spring.model.RegisterRequest;
import koriebruh.dev.management_spring.model.RegisterResponse;
import koriebruh.dev.management_spring.repository.AdminRepository;
import koriebruh.dev.management_spring.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminService {

    // debug info
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterResponse registerAdmin(RegisterRequest registerRequest) {

        Admin admin = new Admin();
        String encryptPsw = passwordEncoder.encode(registerRequest.getPassword());

        admin.setUsername(registerRequest.getUsername());
        admin.setPassword(encryptPsw);
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

    public LoginResponse loginAdmin(LoginRequest loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        logger.info("username"+loginRequest.getUsername());
        logger.info("pass"+loginRequest.getPassword());

        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())){
            throw new IllegalArgumentException("Invalid username or Invalid password");
        }


        String token = jwtUtil.generateToken(admin.getUsername(), String.valueOf(admin.getId()));
        return new LoginResponse(admin.getId(), token, "login successfully");
    }

}
