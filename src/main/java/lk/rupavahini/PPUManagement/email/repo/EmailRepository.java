/*
 *Time   :- 6:15 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.email.repo;

import lk.rupavahini.PPUManagement.email.entity.EmailMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailMsg,Integer> {
}
