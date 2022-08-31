package lk.rupavahini.PPUManagement.asset.ppu.dao;


import lk.rupavahini.PPUManagement.asset.ppu.entity.Ppu;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PpuDao extends JpaRepository<Ppu, Integer> {
    Ppu findFirstByOrderByIdDesc();
}
