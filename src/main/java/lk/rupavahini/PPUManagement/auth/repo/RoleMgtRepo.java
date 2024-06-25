/*
 *Time   :- 12:05 AM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.auth.repo;

import lk.rupavahini.PPUManagement.auth.entity.RoleMgt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleMgtRepo extends JpaRepository<RoleMgt,Integer> {

    @Query(value = "SELECT * FROM auth_role u WHERE u.role_desc = :desc",
            nativeQuery = true)
    RoleMgt findRoleMgtsByDesc(@Param("desc")String desc);

    @Modifying
    @Query(value = "insert into auth_user_role values (0,:adminid, :authRoleId)",
            nativeQuery = true)
    @Transactional
    void insertauth_user_role(@Param("adminid") Integer adminid, @Param("authRoleId") Integer authRoleId);

    @Modifying
    @Query(value = "delete from auth_user_role where adminid=:adminid ",
            nativeQuery = true)
    @Transactional
    void deleteauthuserrole(@Param("adminid") Integer adminid);

}
