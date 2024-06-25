package lk.rupavahini.PPUManagement.asset.sponsor.dao;


import lk.rupavahini.PPUManagement.asset.sponsor.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorDao extends JpaRepository<Sponsor, Integer> {
    Sponsor findFirstByOrderByIdDesc();

    @Query(value = "select username from sponsor  where id =:id",
            nativeQuery = true)
    String getusernamebyid(@Param("id") int id);
<<<<<<< HEAD

    Sponsor findSponsorByUsername(String username);
=======
>>>>>>> 4609734 (Initial commit)
}
