package com.unesco.core.services.dataService.schedule.lessonService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.repositories.LessonRepository;
import com.unesco.core.services.dataService.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonDataService implements ILessonDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonDTO> getPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) lessonRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<LessonEntity> entitys = lessonRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<LessonDTO> result = new ArrayList<LessonDTO>();
        for (LessonEntity e: entitys) {
            result.add((LessonDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<LessonDTO> getAll()
    {
        List<LessonDTO> modelList = new ArrayList<>();
        Iterable<LessonEntity> entityList = lessonRepository.findAll();
        for (LessonEntity item: entityList) {
            LessonDTO model = (LessonDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonDTO get(long id)
    {
        LessonEntity entity = lessonRepository.findOne(id);
        LessonDTO model = (LessonDTO) mapperService.toDto(entity);
        return model;
    }

    public LessonDTO getDisciplineIdAndGroupIdAndProfessorId(long disciplineId, long groupId, long professorId)
    {
        LessonEntity entity = lessonRepository.findByDisciplineIdAndGroupIdAndProfessorId(disciplineId, groupId, professorId);
        LessonDTO model = (LessonDTO) mapperService.toDto(entity);
        return model;
    }

    public List<LessonDTO> getByProfessorId(long professorId)
    {
        List<LessonDTO> modelList = new ArrayList<>();
        List<LessonEntity> entityList = lessonRepository.findByProfessorId(professorId);
        for (LessonEntity item: entityList) {
            LessonDTO model = (LessonDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public void delete(long id)
    {
        lessonRepository.delete(id);
    }

    public LessonDTO save(LessonDTO lesson)
    {
        LessonEntity entity = (LessonEntity) mapperService.toEntity(lesson);
        LessonEntity model = lessonRepository.save(entity);
        lesson = (LessonDTO) mapperService.toDto(model);
        return lesson;
    }

}