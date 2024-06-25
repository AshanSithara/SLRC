package lk.rupavahini.PPUManagement.asset.team.dao;


import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDao extends JpaRepository<Team, Integer > {
    Team findByTeamName(String teamName);
}
