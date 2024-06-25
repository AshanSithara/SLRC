/*
 *Time   :- 1:09 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.asset.commonAsset.model;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileModel {


    private String name;

    private String username;

    private String nic;

    private String mobileOne;

    private String email;

    private String address;

    private String gender;

    private String title;

    private String designation;

    private String civilStatus;

    private String employeeStatus;

    private String dateOfBirth;

    public ProfileModel( String username, String mobileOne) {
        this.username = username;
        this.mobileOne = mobileOne;
    }

    public ProfileModel(String name, String username, String mobileOne, String email, String address) {
        this.name = name;
        this.username = username;
        this.mobileOne = mobileOne;
        this.email = email;
        this.address = address;
    }
}
