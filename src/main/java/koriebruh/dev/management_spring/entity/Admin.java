package koriebruh.dev.management_spring.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt ;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt ;

    // >ragu bg<
    @OneToMany(mappedBy = "createdBy")
    private List<Category> categories ;

    @OneToMany(mappedBy = "createdBy")
    private List<Supplier> suppliers ;

    @OneToMany(mappedBy = "createdBy")
    private  List<Item>items ;
}
