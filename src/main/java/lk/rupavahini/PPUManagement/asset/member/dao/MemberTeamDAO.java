package lk.rupavahini.PPUManagement.asset.member.dao;

import lk.rupavahini.PPUManagement.asset.employee.entity.MemberTeam;
import lk.rupavahini.PPUManagement.asset.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTeamDAO extends JpaRepository<MemberTeam,Integer> {

    MemberTeam findMemberTeamsByMember(Member member);

    @Query(value = "SELECT * FROM member_team u WHERE u.member_id = :memberid",
            nativeQuery = true)
    MemberTeam findMemberTeamsByMemberaa(
            @Param("memberid") Integer id);

}
