package com.unesco.core.services.account.professorService;

import com.unesco.core.entities.account.Professor;
import com.unesco.core.entities.account.User;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorDataService implements IProfessorDataService {
    @Autowired
    private IMapperService mapperService;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private IUserDataService userDataService;

    public List<ProfessorModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) professorRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Professor> entitys = professorRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<ProfessorModel> result = new ArrayList<ProfessorModel>();
        for (Professor e: entitys) {
            result.add((ProfessorModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<ProfessorModel> GetAll()
    {
        List<ProfessorModel> modelList = new ArrayList<>();
        Iterable<Professor> entityList = professorRepository.findAll();
        for (Professor item: entityList) {
            ProfessorModel model = (ProfessorModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public List<ProfessorModel> GetAllByDepartament(long departmentId)
    {
        List<ProfessorModel> modelList = new ArrayList<>();
        Iterable<Professor> entityList = professorRepository.findAllByDepartmentId(departmentId);
        for (Professor item: entityList) {
            ProfessorModel model = (ProfessorModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public ProfessorModel Get(long id)
    {
        Professor entity = professorRepository.findByUserId(id);
        ProfessorModel model = (ProfessorModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        Professor entity = professorRepository.findByUserId(id);
        professorRepository.delete(entity.getId());
    }

    public ProfessorModel Save(ProfessorModel professor)
    {
        Professor entity = (Professor) mapperService.toEntity(professor);
        User user = (User) mapperService.toEntity(userDataService.GetByUsername(entity.getUser().getUsername()));
        entity.setUser(user);
        Professor model = professorRepository.save(entity);
        professor = (ProfessorModel) mapperService.toModel(model);
        return professor;
    }
}
