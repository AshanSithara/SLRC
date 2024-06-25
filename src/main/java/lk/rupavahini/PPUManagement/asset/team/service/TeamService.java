package lk.rupavahini.PPUManagement.asset.team.service;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.TeamModel;
import lk.rupavahini.PPUManagement.asset.team.dao.TeamDao;
import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import lk.rupavahini.PPUManagement.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@CacheConfig( cacheNames = {"team"} ) // tells Spring where to store cache for this class
public class TeamService implements AbstractService<Team, Integer > {
    private final TeamDao teamDao;

    @Autowired
    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Cacheable
    public List<TeamModel> findAllnew() {
        List<Team> all = teamDao.findAll();
        List<TeamModel> teamModels=new ArrayList<>();
        for (Team team:all){
            teamModels.add(new TeamModel(team.getId(),team.getTeamName()));
        }
        return teamModels;
    }

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Cacheable
    public Team findById(Integer id) {
        return teamDao.getOne(id);
    }


    @Caching( evict = {@CacheEvict( value = "team", allEntries = true )},
            put = {@CachePut( value = "team", key = "#team.id" )} )
    public Team persist(Team team) {
        team.setTeamName(team.getTeamName().toUpperCase());
        return teamDao.save(team);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        teamDao.deleteById(id);
        return true;
    }

    @Cacheable
    public List< Team > search(Team team) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< Team > teamExample = Example.of(team, matcher);
        return teamDao.findAll(teamExample);
    }

    @Cacheable
    public Team findByTeamName(String teamName) {
        return teamDao.findByTeamName(teamName);
    }
}
