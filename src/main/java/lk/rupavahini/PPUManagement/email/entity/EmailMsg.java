/*
 *Time   :- 6:10 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.email.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EmailMsg")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmailMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String name;

    @Column(name = "subject",  length = 1000)
    private String subject;

    @Column(name = "text",  length = 5000)
    private String text;

    private String mobilenumber;

    private int type;

    public EmailMsg(String email, String name, String subject, String text, String mobilenumber, int type) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.text = text;
        this.mobilenumber = mobilenumber;
        this.type = type;
    }
}
