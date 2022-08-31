/*
 *Time   :- 5:53 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.email.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmailModel {

    private  int id;

    private String email;

    private String name;

    private String subject;

    private String text;

    private String mobilenumber;

    public EmailModel(String email, String name, String subject, String text, String mobilenumber) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.text = text;
        this.mobilenumber = mobilenumber;
    }

    public EmailModel(int id, String subject, String text) {
        this.id = id;
        this.subject = subject;
        this.text = text;
    }
}
