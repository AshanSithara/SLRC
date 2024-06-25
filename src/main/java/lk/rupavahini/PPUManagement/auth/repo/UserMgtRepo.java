/*
 *Time   :- 11:57 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.auth.repo;

import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserMgtRepo extends JpaRepository<UserMgt,Integer> {

    public UserMgt findUserMgtsByUsername(String username);
    UserMgt findByUsername(String username);

    @Modifying
    @Query(value = "delete from user_mgt where adminid=:adminid ",
            nativeQuery = true)
    @Transactional
    void deleteSystemUser(@Param("adminid") Integer adminid);

}
