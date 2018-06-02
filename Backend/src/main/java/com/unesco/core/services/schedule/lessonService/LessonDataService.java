package com.unesco.core.services.schedule.lessonService;

import com.unesco.core.entities.schedule.Lesson;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.LessonModel;
import com.unesco.core.repositories.LessonRepository;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.mapperService.IMapperService;
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
    @Autowired
    private IProfessorDataService professorDataService;

    public List<LessonModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) lessonRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Lesson> entitys = lessonRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<LessonModel> result = new ArrayList<LessonModel>();
        for (Lesson e: entitys) {
            result.add((LessonModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<LessonModel> GetAll()
    {
        List<LessonModel> modelList = new ArrayList<>();
        Iterable<Lesson> entityList = lessonRepository.findAll();
        for (Lesson item: entityList) {
            LessonModel model = (LessonModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public LessonModel Get(long id)
    {
        Lesson entity = lessonRepository.findOne(id);
        LessonModel model = (LessonModel) mapperService.toModel(entity);
        return model;
    }

    public LessonModel GetDisciplineIdAndGroupIdAndProfessorId(long disciplineId, long groupId, long professorId)
    {
        Lesson entity = lessonRepository.findByDisciplineIdAndGroupIdAndProfessorId(disciplineId, groupId, professorId);
        LessonModel model = (LessonModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        lessonRepository.delete(id);
    }

    public LessonModel Save(LessonModel lesson)
    {
        Lesson entity = (Lesson) mapperService.toEntity(lesson);
        Lesson model = lessonRepository.save(entity);
        lesson = (LessonModel) mapperService.toModel(model);
        return lesson;
    }

}