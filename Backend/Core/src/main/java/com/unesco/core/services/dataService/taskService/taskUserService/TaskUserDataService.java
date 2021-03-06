package com.unesco.core.services.dataService.taskService.taskUserService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.repositories.task.TaskRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TaskUserDataService implements ITaskUserDataService
{
    @Autowired
    private TaskRepository _taskRepository;
    @Autowired
    private MapperService _mapperService;

    @Override
    public ResponseStatusDTO<TaskUserDTO> update(TaskUserDTO newTUModel) {
        ResponseStatusDTO<TaskUserDTO> result;
       try {
           TaskUser base = _taskRepository.findById(newTUModel.getId());
           TaskUser entity = (TaskUser) _mapperService.toEntity(newTUModel);
           if(base != null) {
               if(entity.getDateRequired() != null) {
                   if (entity.getDateRequired().before(entity.getDateCreate())) {
                       entity.setDateRequired(null);
                   }
               }

               if (TaskStatusType.getById(entity.getStatus()) != null) {
                   base.setStatus(TaskStatusType.getById(entity.getStatus()).ordinal());
               }

               if (entity.getResponse() != null) {
                   base.setResponse(entity.getResponse());
               }

               if (entity.getDateRequired() != null) {
                   base.setDateRequired(entity.getDateRequired());
               }

               if (newTUModel.getFiles()!=null) {
                   Set<FileDescription> files = new HashSet<>();
                   for (FileDescriptionModel file : newTUModel.getFiles()) {
                       files.add((FileDescription) _mapperService.toEntity(file));
                   }
                   base.setFiles(files);
               }


               result = new ResponseStatusDTO<>(StatusTypes.OK);
               result.setData((TaskUserDTO) _mapperService.toDto(_taskRepository.save(base)));
               result.addMessage("Задача пользователя успешно изменена");
           }
           else{
               result = new ResponseStatusDTO<>(StatusTypes.ERROR);
               result.addErrors("Задача пользователя не найдена");
           }
       }
       catch (Exception e) {
           result = new ResponseStatusDTO<>(StatusTypes.ERROR);
           result.addErrors("Не удалось обновить задачу для пользователя");
           result.addErrors(e.getMessage());
       }
       return result;
   }

    @Override
    public ResponseStatusDTO<TaskUserDTO> changeStatus(long TUId, int statusId) {
       ResponseStatusDTO<TaskUserDTO> result;
       try {
           TaskUser base = _taskRepository.findById(TUId);

           if(TaskStatusType.getById(statusId) != null) {
               base.setStatus(TaskStatusType.getById(statusId).ordinal());
           }

           result = new ResponseStatusDTO<>(StatusTypes.OK);
           result.setData((TaskUserDTO) _mapperService.toDto(_taskRepository.save(base)));
           result.addMessage("Статус успешно изменён");
       }
       catch (Exception e) {
           result = new ResponseStatusDTO<>(StatusTypes.ERROR);
           result.addErrors("Не удалось изменить статус");
           result.addErrors(e.getMessage());
       }
       return result;
   }

    // ЗАМЕНИТЬ НА ФИЛЬТРАЦИЮ!!!!!!!!
    // Либо вообще убрать, и фильтровать в менеджере
    @Override
    public List<TaskUserDTO> getByExecutor(long id) {
        Iterable<TaskUser> tasks = _taskRepository.findByExecutorId(id);
        List<TaskUserDTO> result = new ArrayList<>();
        for (TaskUser item : tasks) {
            result.add((TaskUserDTO)_mapperService.toDto(item));
        }
        return result;
    }

    // ЗАМЕНИТЬ НА ФИЛЬТРАЦИЮ!!!!!!!!
    // Либо вообще убрать, и фильтровать в менеджере
    @Override
    public List<TaskUserDTO> getByTDID(long id) {
        Iterable<TaskUser> tasks = _taskRepository.findByTaskDescriptionId(id);
        List<TaskUserDTO> result = new ArrayList<>();
        for (TaskUser item : tasks) {
            result.add((TaskUserDTO)_mapperService.toDto(item));
        }
        return result;
    }

    @Override
    public ResponseStatusDTO<TaskUserDTO> save(TaskUserDTO taskUserDTO) {
       TaskUser entity = (TaskUser) _mapperService.toEntity(taskUserDTO);
       ResponseStatusDTO<TaskUserDTO> result = new ResponseStatusDTO<>();
       try {
           Date date= new Date();
           long time = date.getTime();
           Timestamp now = new Timestamp(time);
           entity.setDateCreate(now);
           if(entity.getDateRequired()!=null) {
               if (entity.getDateRequired().before(entity.getDateCreate())) {
                   entity.setDateRequired(null);
               }
           }
           entity = _taskRepository.save(entity);
           result.setData((TaskUserDTO) _mapperService.toDto(entity));
           result.setStatus(StatusTypes.OK);
           result.addMessage("Задача пользователя успешно сохранена");
       }
       catch (Exception e) {
           result = new ResponseStatusDTO<>(StatusTypes.ERROR);
           result.addErrors("Не удалось создать задачу для пользователя: " + taskUserDTO.getExecutor().getUserFIO());
           result.addErrors(e.getMessage());
           return result;
       }
       result.setData((TaskUserDTO) _mapperService.toDto(entity));
       return result;
   }

    @Override
    public ResponseStatusDTO<TaskUserDTO> delete(long id) {
      ResponseStatusDTO<TaskUserDTO> result;
      try {
          result = new ResponseStatusDTO<>(StatusTypes.OK);
          TaskUser item = _taskRepository.findById(id);
          if(item != null) {
              result.setData((TaskUserDTO) _mapperService.toDto(item));
              _taskRepository.delete(id);
              result.addMessage("Задача пользователя удалена");
          }
          else {
              result.setStatus(StatusTypes.ERROR);
              result.addErrors("Задача пользователя не найдена");
          }
      }
      catch (Exception e) {
          result = new ResponseStatusDTO<>(StatusTypes.ERROR);
          if(e instanceof DataIntegrityViolationException) {
              result.addErrors("Удаление не удалось. У объекта есть зависимости.");
          }
          result.addErrors("Удаление не удалось");
      }
      return result;
   }

    @Override
    public TaskUserDTO get(long id) {
      return (TaskUserDTO) _mapperService.toDto(_taskRepository.findById(id));
   }

    @Override
    public List<TaskUserDTO> getAll() {
      List<TaskUserDTO> result = new ArrayList<>();
      Iterable<TaskUser> entities = _taskRepository.findAll();
      for (TaskUser item:entities) {
         result.add((TaskUserDTO) _mapperService.toDto(item));
      }
      return result;
   }
}
