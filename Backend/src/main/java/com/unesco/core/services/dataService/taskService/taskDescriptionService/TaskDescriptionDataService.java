package com.unesco.core.services.dataService.taskService.taskDescriptionService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.enums.TaskType;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.repositories.task.TaskDescriptionRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TaskDescriptionDataService implements ITaskDescriptionDataService {
    @Autowired
    private MapperService _mapperService;
    @Autowired
    private TaskDescriptionRepository _taskDescriptionRepository;

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> save(TaskDescriptionDTO taskDescriptionDTO) {
        TaskDescription entity = (TaskDescription) _mapperService.toEntity(taskDescriptionDTO);
        ResponseStatusDTO<TaskDescriptionDTO> result = new ResponseStatusDTO<>();
        try {
            Date date = new Date();
            long time = date.getTime();
            Timestamp now = new Timestamp(time);
            entity.setDateCreate(now);
            if (entity.getDateRequired() != null) {
                if (entity.getDateRequired().before(entity.getDateCreate())) {
                    entity.setDateRequired(null);
                }
            }
            entity = _taskDescriptionRepository.save(entity);
            result.setData((TaskDescriptionDTO) _mapperService.toDto(entity));
            result.setStatus(StatusTypes.OK);
            result.addMessage("Задача успешно создана");
        } catch (Exception e) {
            result = new ResponseStatusDTO<>(StatusTypes.ERROR);
            result.addErrors("Не удалось создать задачу");
            result.addErrors(e.getMessage());
        }
        return result;
    }

    // ЗАМЕНИТЬ НА ФИЛЬТРАЦИЮ!!!!!!!!
    // Либо вообще убрать, и фильтровать в менеджере
    @Override
    public List<TaskDescriptionDTO> getByCreator(long id) {
        Iterable<TaskDescription> entities = _taskDescriptionRepository.findAllByCreatorId(id);
        List<TaskDescriptionDTO> result = new ArrayList<>();
        for (TaskDescription item : entities) {
            result.add((TaskDescriptionDTO) _mapperService.toDto(item));
        }
        return result;
    }

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> update(TaskDescriptionDTO newTDModel) {
        ResponseStatusDTO<TaskDescriptionDTO> result = new ResponseStatusDTO<>();
        try {
            TaskDescription base = _taskDescriptionRepository.findById(newTDModel.getId());
            TaskDescription entity = (TaskDescription) _mapperService.toEntity(newTDModel);
            if (base != null) {
                if(entity.getDateRequired() != null) {
                    if (entity.getDateRequired().before(entity.getDateCreate())) {
                        entity.setDateRequired(null);
                    }
                }

                if (entity.getName() != null) {
                    base.setName(entity.getName());
                }

                if (entity.getDescription() != null) {
                    base.setDescription(entity.getDescription());
                }

                if (TaskStatusType.getById(entity.getStatus()) != null) {
                    base.setStatus(TaskStatusType.getById(entity.getStatus()).ordinal());
                }

                if (newTDModel.getFiles() != null) {
                    Set<FileDescription> files = new HashSet<>();
                    for (FileDescriptionModel file : newTDModel.getFiles()) {
                        files.add((FileDescription) _mapperService.toEntity(file));
                    }
                    base.setFiles(files);
                }

                if (TaskType.getById(entity.getType()) != null) {
                    base.setType(TaskType.getById(entity.getType()).ordinal());
                }

                if (entity.getDateRequired() != null) {
                    base.setDateRequired(entity.getDateRequired());
                }

                _taskDescriptionRepository.save(base);
                result.setData((TaskDescriptionDTO) _mapperService.toDto(base));
                result.setStatus(StatusTypes.OK);
                result.addMessage("Задача успешно изменена");
            } else {
                result = new ResponseStatusDTO<>(StatusTypes.ERROR);
                result.addErrors("Задача не найдена");
            }
        } catch (Exception e) {
            result = new ResponseStatusDTO<>(StatusTypes.ERROR);
            result.addErrors("Не удалось обновить задачу");
            result.addErrors(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> changeStatus(long TDId, int statusId) {
        ResponseStatusDTO<TaskDescriptionDTO> result;
        try {
            TaskDescription base = _taskDescriptionRepository.findById(TDId);

            if (TaskStatusType.getById(statusId) != null) {
                base.setStatus(TaskStatusType.getById(statusId).ordinal());
            }

            _taskDescriptionRepository.save(base);
            result = new ResponseStatusDTO<>(StatusTypes.OK);
            result.addMessage("Статус успешно изменён");
        } catch (Exception e) {
            result = new ResponseStatusDTO<>(StatusTypes.ERROR);
            result.addErrors("Не удалось изменить статус");
            result.addErrors(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseStatusDTO<TaskDescriptionDTO> delete(long id) {
        ResponseStatusDTO<TaskDescriptionDTO> result;
        try {
            _taskDescriptionRepository.delete(id);
            result = new ResponseStatusDTO<>(StatusTypes.OK);
            result.addMessage("Задача удалена");
        } catch (Exception e) {
            result = new ResponseStatusDTO<>(StatusTypes.ERROR);
            if (e instanceof DataIntegrityViolationException) {
                result.addErrors("Удаление не удалось. У объекта есть зависимости.");
            }
            result.addErrors("Удаление не удалось");
        }
        return result;
    }

    @Override
    public TaskDescriptionDTO get(long id) {
        return (TaskDescriptionDTO) _mapperService.toDto(_taskDescriptionRepository.findById(id));
    }

    @Override
    public List<TaskDescriptionDTO> getAll() {
        List<TaskDescriptionDTO> result = new ArrayList<>();
        Iterable<TaskDescription> entities = _taskDescriptionRepository.findAll();
        for (TaskDescription item : entities) {
            result.add((TaskDescriptionDTO) _mapperService.toDto(item));
        }
        return result;
    }
}
