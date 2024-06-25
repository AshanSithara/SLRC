package lk.rupavahini.PPUManagement.auth.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "UserMgt")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMgt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "admin Id is compulsory")
    private int adminID;


    @NotNull(message = "username is compulsory")
    @Size(min = 5, max = 150, message = "username should be at least 5 characters and less than 150 characters")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Password is compulsory")
    @Size(min = 5, message = "Password should be at least 5 characters and less than 50 characters")
    private String password;

    private String type;

    private String status;

<<<<<<< HEAD
<<<<<<< HEAD
    private String mobilenumber;

=======
>>>>>>> 4609734 (Initial commit)
=======
    private String mobilenumber;

>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "adminid"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<RoleMgt> roles;

    public UserMgt(@NotNull(message = "username is compulsory") @Size(min = 5, max = 150, message = "username should be at least 5 characters and less than 150 characters") String username, @NotNull(message = "Password is compulsory") @Size(min = 5, message = "Password should be at least 5 characters and less than 50 characters") String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public UserMgt(@NotNull(message = "username is compulsory") @Size(min = 5, max = 150, message = "username should be at least 5 characters and less than 150 characters") String username, @NotNull(message = "Password is compulsory") @Size(min = 5, message = "Password should be at least 5 characters and less than 50 characters") String password, String type, String status, String mobilenumber) {
=======
    public UserMgt(@NotNull(message = "username is compulsory") @Size(min = 5, max = 150, message = "username should be at least 5 characters and less than 150 characters") String username, @NotNull(message = "Password is compulsory") @Size(min = 5, message = "Password should be at least 5 characters and less than 50 characters") String password, String type, String status) {
>>>>>>> 4609734 (Initial commit)
=======
    public UserMgt(@NotNull(message = "username is compulsory") @Size(min = 5, max = 150, message = "username should be at least 5 characters and less than 150 characters") String username, @NotNull(message = "Password is compulsory") @Size(min = 5, message = "Password should be at least 5 characters and less than 50 characters") String password, String type, String status, String mobilenumber) {
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        this.username = username;
        this.password = password;
        this.type = type;
        this.status = status;
<<<<<<< HEAD
<<<<<<< HEAD
        this.mobilenumber = mobilenumber;
=======
>>>>>>> 4609734 (Initial commit)
=======
        this.mobilenumber = mobilenumber;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    }
}
