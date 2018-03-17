package com.unesco.core.services.taskDataService;

import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.TaskDescriptionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITaskDescriptionDataService {
   List<TaskDescriptionModel> getAllTaskDescription();
   List<TaskDescriptionModel> getTaskDescriptionBySubTasks(long id);
   List<TaskDescriptionModel> getTaskDescriptionByCreator(long id);
   void createNewTaskDescription(TaskDescriptionModel td);
   void updateTaskDescription(TaskDescriptionModel td);
   void deleteTaskDescription(long id);
   TaskDescriptionModel getTaskDescriptionById(long id);
   List<TaskDescriptionModel> EntityToModel(List<TaskDescription> tds);
   List<TaskDescriptionModel> EntityToModel(Iterable<TaskDescription> tds);

}
