package lk.rupavahini.PPUManagement.asset.ppu.service;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.StudioModel;
import lk.rupavahini.PPUManagement.asset.ppu.dao.PpuDao;
import lk.rupavahini.PPUManagement.asset.ppu.entity.Ppu;
import lk.rupavahini.PPUManagement.asset.studio.dao.StudioDao;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lk.rupavahini.PPUManagement.util.interfaces.AbstractService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig( cacheNames = "ppu" )
public class PpuService implements AbstractService<Ppu, Integer> {
    private final PpuDao ppuDao;

    public PpuService(PpuDao ppuDao) {
        this.ppuDao = ppuDao;
    }


    public List<Ppu> findAll() {
        return ppuDao.findAll();
    }

    public Ppu findById(Integer id) {
        return ppuDao.getOne(id);
    }



    public Ppu persist(Ppu ppu) {
        return ppuDao.save(ppu);
    }

    public boolean delete(Integer id) {
        ppuDao.deleteById(id);
        return false;
    }


    public List<PpuModel> getallPpu(){
        List<Ppu> all = ppuDao.findAll();
        List<PpuModel> ppuModels=new ArrayList<>();
        for (Ppu ppu:all){
            ppuModels.add(new PpuModel(ppu.getId(),ppu.getPPUname()));
        }
        return ppuModels;
    }


    public List<Ppu> search(Ppu ppu) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Ppu> SponserExample = Example.of(ppu, matcher);
        return ppuDao.findAll(SponserExample);
    }

    public Ppu lastPpu(){
        return ppuDao.findFirstByOrderByIdDesc();
    }
}
