package lk.rupavahini.PPUManagement.auth.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "auth_role")
public class RoleMgt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_role_id")
    private int id;

    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;

}
