package koriebruh.dev.management_spring.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admins")

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(nullable = false, length = 50)
    private String username ;

    @Column(nullable = false, length = 500)
    private String password ;

    @Column(length = 500)
    private String email ;

    @Column(name = "created_at")
    private LocalDateTime createdAt ;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt ;

}
