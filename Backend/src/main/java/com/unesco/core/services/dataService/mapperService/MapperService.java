package com.unesco.core.services.dataService.mapperService;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.entities.account.ProfessorEntity;
import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.entities.account.StudentEntity;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.file.FileByteCode;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.journal.LessonEventEntity;
import com.unesco.core.entities.journal.PointEntity;
import com.unesco.core.entities.journal.PointTypeEntity;
import com.unesco.core.entities.journal.VisitationConfigEntity;
import com.unesco.core.entities.news.NewsEntity;
import com.unesco.core.entities.schedule.*;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.entities.task.TaskUser;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

@Service
public class MapperService implements IMapperService {

    public <T> Object toEntity(T model) {

        if (model == null)
            return null;

        if (model instanceof LessonEventDTO)
            return lessonEventToEntity((LessonEventDTO) model);

        if (model instanceof PointDTO)
            return pointToEntity((PointDTO) model);

        if (model instanceof PointTypeDTO)
            return pointTypeToEntity((PointTypeDTO) model);

        if (model instanceof LessonDTO)
            return lessonToEntity((LessonDTO) model);

        if (model instanceof ProfessorDTO)
            return professorToEntity((ProfessorDTO) model);

        if (model instanceof StudentDTO)
            return studentToEntity((StudentDTO) model);

        if (model instanceof PairDTO)
            return pairToEntity((PairDTO) model);

        if (model instanceof RoleDTO)
            return roleToEntity((RoleDTO) model);

        if (model instanceof UserDTO)
            return userToEntity((UserDTO) model);

        if (model instanceof InstituteDTO)
            return instituteToEntity((InstituteDTO) model);

        if (model instanceof DepartmentDTO)
            return departmentToEntity((DepartmentDTO) model);

        if (model instanceof GroupDTO)
            return groupToEntity((GroupDTO) model);

        if (model instanceof DisciplineDTO)
            return disciplineToEntity((DisciplineDTO) model);

        if (model instanceof FieldOfKnowledgeDTO)
            return fieldOfKnowledgeToEntity((FieldOfKnowledgeDTO) model);

        if (model instanceof RoomDTO)
            return roomToEntity((RoomDTO) model);

        if (model instanceof TaskUserModel)
            return taskuserToEntity((TaskUserModel) model);

        if (model instanceof NewsDTO)
            return newsToEntity((NewsDTO) model);

        if (model instanceof TaskDescriptionModel)
            return taskDescriptionToEntity((TaskDescriptionModel) model);

        if (model instanceof FileByteCodeModel)
            return fileByteCodeToEntity((FileByteCodeModel) model);

        if (model instanceof FileDescriptionModel)
            return fileDescriptionToEntity((FileDescriptionModel) model);

        if (model instanceof PairTypeDTO)
            return pairTypeToEntity((PairTypeDTO) model);

        if (model instanceof VisitationConfigDTO)
            return visitationConfigToEntity((VisitationConfigDTO) model);

        return new Exception("Not found " + model.getClass().toString() + " model class");
    }

    public <T> Object toDto(T entity) {

        if (entity == null)
            return null;

        if (entity instanceof LessonEventEntity)
            return lessonEventToDto((LessonEventEntity) entity);

        if (entity instanceof PointEntity)
            return pointToDto((PointEntity) entity);

        if (entity instanceof PointTypeEntity)
            return pointTypeToDto((PointTypeEntity) entity);

        if (entity instanceof LessonEntity)
            return lessonToDto((LessonEntity) entity);

        if (entity instanceof ProfessorEntity)
            return professorToDto((ProfessorEntity) entity);

        if (entity instanceof StudentEntity)
            return studentToDto((StudentEntity) entity);

        if (entity instanceof PairEntity)
            return pairToDto((PairEntity) entity);

        if (entity instanceof RoleEntity)
            return roleToDto((RoleEntity) entity);

        if (entity instanceof UserEntity)
            return userToDto((UserEntity) entity);

        if (entity instanceof InstituteEntity)
            return instituteToDto((InstituteEntity) entity);

        if (entity instanceof DepartmentEntity)
            return departmentToDto((DepartmentEntity) entity);

        if (entity instanceof GroupEntity)
            return groupToDto((GroupEntity) entity);

        if (entity instanceof DisciplineEntity)
            return disciplineToDto((DisciplineEntity) entity);

        if (entity instanceof FieldOfKnowledgeEntity)
            return fieldOfKnowledgeToDto((FieldOfKnowledgeEntity) entity );

        if (entity instanceof RoomEntity)
            return roomToDto((RoomEntity) entity);

        if (entity instanceof TaskUser)
            return taskuserToDto((TaskUser) entity);

        if (entity instanceof NewsEntity)
            return newsToDto((NewsEntity) entity);

        if (entity instanceof TaskDescription)
            return taskDescriptionToDto((TaskDescription) entity);

        if (entity instanceof FileByteCode)
            return fileByteCodeToDto((FileByteCode) entity);

        if (entity instanceof FileDescription)
            return fileDescriptionToDto((FileDescription) entity);

        if (entity instanceof PairTypeEntity)
            return pairTypeToDto((PairTypeEntity) entity);

        if (entity instanceof VisitationConfigEntity)
            return visitationConfigToDTO((VisitationConfigEntity) entity);

        return new Exception("Not found " + entity.getClass().toString() + " entity class");
    }

    public VisitationConfigEntity visitationConfigToEntity(VisitationConfigDTO Dto) {
        if (Dto == null) return null;
        VisitationConfigEntity Entity = new VisitationConfigEntity();

        Entity.setId(Dto.getId());
        Entity.setAutoGenerated(Dto.isAutoGenerated());
        Entity.setDates(Dto.getDates());
        Entity.setValue(Dto.getValue());
        Entity.setLesson(lessonToEntity(Dto.getLesson()));
        return Entity;

    }

    public VisitationConfigDTO visitationConfigToDTO(VisitationConfigEntity Entity) {
        if (Entity == null) return null;
        VisitationConfigDTO Dto = new VisitationConfigDTO();

        Dto.setId(Entity.getId());
        Dto.setAutoGenerated(Entity.isAutoGenerated());
        Dto.setDates(Entity.getDates());
        Dto.setValue(Entity.getValue());
        Dto.setLesson(lessonToDto(Entity.getLesson()));
        return Dto;
    }

    public PointDTO pointToDto(PointEntity Entity)
    {
        if (Entity == null) return null;
        PointDTO Dto = new PointDTO();
        Dto.setId(Entity.getId());
        Dto.setStudent(studentToDto(Entity.getStudent()));
        Dto.setValue(Entity.getValue());
        Dto.setPair(pairToDto(Entity.getPair()));
        Dto.setType(pointTypeToDto(Entity.getType()));
        Dto.setDate(Entity.getDate());
        return Dto;
    }
    public PointEntity pointToEntity(PointDTO Dto)
    {
        if (Dto == null) return null;
        PointEntity Entity = new PointEntity();
        Entity.setId(Dto.getId());
        Entity.setStudent(studentToEntity(Dto.getStudent()));
        Entity.setValue(Dto.getValue());
        Entity.setPair(pairToEntity(Dto.getPair()));
        Entity.setType(pointTypeToEntity(Dto.getType()));
        Entity.setDate(Dto.getDate());
        return Entity;
    }

    public LessonEventDTO lessonEventToDto(LessonEventEntity Entity)
    {
        if (Entity == null) return null;
        LessonEventDTO Dto = new LessonEventDTO();
        Dto.setId(Entity.getId());
        Dto.setDate(null);
        if(Entity.getDate() != null) {
            Date Dt = new Date((long)Entity.getDate().getTime());
            Dto.setDate(Dt);
        }
        Dto.setComment(Entity.getComment());
        Dto.setMaxValue(Entity.getMaxValue());
        Dto.setLesson(lessonToDto(Entity.getLesson()));
        Dto.setType(pointTypeToDto(Entity.getType()));
        return Dto;
    }
    public LessonEventEntity lessonEventToEntity(LessonEventDTO Dto)
    {
        if (Dto == null) return null;
        LessonEventEntity Entity = new LessonEventEntity();
        Entity.setId(Dto.getId());
        Entity.setDate(null);
        if(Dto.getDate() != null) {
            Timestamp ts = new Timestamp(Dto.getDate().getTime());
            Entity.setDate(ts);
        }
        Entity.setComment(Dto.getComment());
        Entity.setMaxValue(Dto.getMaxValue());
        Entity.setLesson(lessonToEntity(Dto.getLesson()));
        Entity.setType(pointTypeToEntity(Dto.getType()));
        return Entity;
    }

    public PointTypeDTO pointTypeToDto(PointTypeEntity Entity)
    {
        if (Entity == null) return null;
        PointTypeDTO Dto = new PointTypeDTO();
        Dto.setId(Entity.getId());
        Dto.setName(Entity.getName());
        return Dto;
    }
    public PointTypeEntity pointTypeToEntity(PointTypeDTO Dto)
    {
        if (Dto == null) return null;
        PointTypeEntity Entity = new PointTypeEntity();
        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        return Entity;
    }

    public TaskDescriptionModel taskDescriptionToDto(TaskDescription Entity) {
        if (Entity == null) return null;
        TaskDescriptionModel Dto = new TaskDescriptionModel();
        Dto.setId(Entity.getId());
        Dto.setCreator(userToDto(Entity.getCreator()));
        Dto.setUsers(new ArrayList<>());
        Dto.setDescription(Entity.getDescription());
        Dto.setName(Entity.getName());
        Dto.setStatus(Entity.getStatus());
        Dto.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        List<TaskUserModel> tasks = new ArrayList<>();
        /*for (Task t: Entity.getSubTasks()) {
            tasks.add(TaskToDto(t));
        }*/
        Dto.setTaskUsers(tasks);
        List<FileDescriptionModel> files = new ArrayList<>();
        for (FileDescription t: Entity.getFiles()) {
            files.add(fileDescriptionToDto(t));
        }
        Dto.setFiles(files);
        return Dto;
    }

    public TaskDescription taskDescriptionToEntity(TaskDescriptionModel Dto) {
        if (Dto == null) return null;
        TaskDescription Entity = new TaskDescription();
        Entity.setId(Dto.getId());
        Entity.setCreator(userToEntity(Dto.getCreator()));
        Entity.setDescription(Dto.getDescription());
        Entity.setName(Dto.getName());
        List<TaskUser> tasks = new ArrayList<>();
        for (TaskUserModel t : Dto.getTaskUsers()) {
            tasks.add(taskuserToEntity(t));
        }
        Entity.setTaskUsers(tasks);

        Set<FileDescription> files = new HashSet<>();
        for (FileDescriptionModel t : Dto.getFiles()) {
            files.add(fileDescriptionToEntity(t));
        }
        Entity.setFiles(files);
        return Entity;
    }

    public TaskUserModel taskuserToDto(TaskUser Entity) {
        if (Entity == null) return null;
        TaskUserModel Dto = new TaskUserModel();
        Dto.setId(Entity.getId());
        Dto.setExecutor(userToDto(Entity.getExecutor()));
        Dto.setResponse(Entity.getResponse());
        Dto.setStatus(Entity.getStatus());
        Dto.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        Dto.setTaskDescriptionId(Entity.getTaskDescription().getId());
        List<FileDescriptionModel> files = new ArrayList<>();
        for (FileDescription t: Entity.getFiles()) {
            files.add(fileDescriptionToDto(t));
        }
        Dto.setFiles(files);
        return Dto;
    }

    public TaskUser taskuserToEntity(TaskUserModel Dto) {
        if (Dto == null) return null;
        TaskUser Entity = new TaskUser();
        Entity.setId(Dto.getId());
        Entity.setExecutor(userToEntity(Dto.getExecutor()));
        Entity.setResponse(Dto.getResponse());
        Entity.setStatus(Dto.getStatus());
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setId(Dto.getTaskDescriptionId());
        Entity.setTaskDescription(taskDescription);
        Set<FileDescription> files = new HashSet<>();
        for (FileDescriptionModel t : Dto.getFiles()) {
            files.add(fileDescriptionToEntity(t));
        }
        Entity.setFiles(files);
        return Entity;
    }

    public NewsDTO newsToDto(NewsEntity Entity)
    {
        if (Entity == null) return null;
        NewsDTO Dto = new NewsDTO();
        Dto.setId(Entity.getId());
        Dto.setAuthor(userToDto(Entity.getAuthor()));
        Dto.setDate(Entity.getDate());
        Dto.setContent(Entity.getContent());
        Dto.setHeader(Entity.getHeader());
        Dto.setId(Entity.getId());
        Dto.setImage(new String(Entity.getImage(), StandardCharsets.UTF_8));
        Dto.setTags(Entity.getTags());
        return Dto;
    }
    public NewsEntity newsToEntity(NewsDTO Dto)
    {
        if (Dto == null) return null;
        NewsEntity Entity = new NewsEntity();
        Entity.setId(Dto.getId());
        Entity.setAuthor(userToEntity(Dto.getAuthor()));
        Entity.setDate(Dto.getDate());
        Entity.setContent(Dto.getContent());
        Entity.setHeader(Dto.getHeader());
        Entity.setId(Dto.getId());
        Entity.setImage(Dto.getImage().getBytes());
        Entity.setTags(Dto.getTags());
        return Entity;
    }

    public ProfessorDTO professorToDto(ProfessorEntity Entity)
    {
        if (Entity == null) return null;
        ProfessorDTO Dto = new ProfessorDTO();
        Dto.setId(Entity.getId());
        Dto.setUser(userToDto(Entity.getUser()));
        Dto.setDepartment((DepartmentDTO) departmentToDto(Entity.getDepartment()));
        return Dto;
    }
    public ProfessorEntity professorToEntity(ProfessorDTO Dto)
    {
        if (Dto == null) return null;
        ProfessorEntity Entity = new ProfessorEntity();
        Entity.setId(Dto.getId());
        Entity.setUser(userToEntity(Dto.getUser()));
        Entity.setDepartment((DepartmentEntity) toEntity(Dto.getDepartment()));
        return Entity;
    }

    public StudentDTO studentToDto(StudentEntity Entity)
    {
        if (Entity == null) return null;
        StudentDTO Dto = new StudentDTO();
        Dto.setId(Entity.getId());
        Dto.setUser(userToDto(Entity.getUser()));
        Dto.setGroup((GroupDTO) groupToDto(Entity.getGroup()));
        return Dto;
    }
    public StudentEntity studentToEntity(StudentDTO Dto)
    {
        if (Dto == null) return null;
        StudentEntity Entity = new StudentEntity();
        Entity.setId(Dto.getId());
        Entity.setUser(userToEntity(Dto.getUser()));
        Entity.setGroup((GroupEntity) toEntity(Dto.getGroup()));
        return Entity;
    }

    public RoomDTO roomToDto(RoomEntity Entity)
    {
        if (Entity == null) return null;
        RoomDTO Dto = new RoomDTO();
        Dto.setId(Entity.getId());
        Dto.setRoom(Entity.getRoom());
        return Dto;
    }
    public RoomEntity roomToEntity(RoomDTO Dto)
    {
        if (Dto == null) return null;
        RoomEntity Entity = new RoomEntity();
        Entity.setId(Dto.getId());
        Entity.setRoom(Dto.getRoom());
        return Entity;
    }

    public LessonDTO lessonToDto(LessonEntity Entity)
    {
        if (Entity == null) return null;
        LessonDTO Dto = new LessonDTO();
        Dto.setId((int) Entity.getId());
        Dto.setDiscipline(disciplineToDto(Entity.getDiscipline()));
        Dto.setGroup(groupToDto(Entity.getGroup()));
        Dto.setProfessor(professorToDto(Entity.getProfessor()));
        return Dto;
    }
    public LessonEntity lessonToEntity(LessonDTO Dto)
    {
        if (Dto == null) return null;
        LessonEntity Entity = new LessonEntity();
        Entity.setId(Dto.getId());
        Entity.setDiscipline(disciplineToEntity(Dto.getDiscipline()));
        Entity.setGroup(groupToEntity(Dto.getGroup()));
        Entity.setProfessor(professorToEntity(Dto.getProfessor()));
        return Entity;
    }

    public PairDTO pairToDto(PairEntity Entity)
    {
        if (Entity == null) return null;
        PairDTO Dto = new PairDTO();
        Dto.setId((int) Entity.getId());
        Dto.setPairNumber(Entity.getPairNumber());
        Dto.setWeektype(Entity.getWeektype());
        Dto.setDayofweek(Entity.getDayofweek());
        Dto.setLesson(lessonToDto(Entity.getLesson()));
        Dto.setRoom(roomToDto(Entity.getRoom()));
        Dto.setOptionally(Entity.isOptionally());
        Dto.setPairType(pairTypeToDto(Entity.getPairType()));
        return Dto;
    }
    public PairEntity pairToEntity(PairDTO Dto)
    {
        if (Dto == null) return null;
        PairEntity Entity = new PairEntity();
        Entity.setId((int) Dto.getId());
        Entity.setPairNumber(Dto.getPairNumber());
        Entity.setWeektype(Dto.getWeektype());
        Entity.setDayofweek(Dto.getDayofweek());
        Entity.setLesson(lessonToEntity(Dto.getLesson()));
        Entity.setRoom(roomToEntity(Dto.getRoom()));
        Entity.setOptionally(Dto.isOptionally());
        Entity.setPairType(pairTypeToEntity(Dto.getPairType()));
        return Entity;
    }
    
    public PairTypeDTO pairTypeToDto(PairTypeEntity Entity)
    {
        if (Entity == null) return null;
        PairTypeDTO Dto = new PairTypeDTO();
        Dto.setId(Entity.getId());
        Dto.setType(Entity.getType());
        return Dto;
    }
    public PairTypeEntity pairTypeToEntity(PairTypeDTO Dto)
    {
        if (Dto == null) return null;
        PairTypeEntity Entity = new PairTypeEntity();
        Entity.setId(Dto.getId());
        Entity.setType(Dto.getType());
        return Entity;
    }

    public UserEntity userToEntity(UserDTO Dto)
    {
        if (Dto == null) return null;
        UserEntity Entity = new UserEntity();

        Entity.setId(Dto.getId());
        Entity.setUsername(Dto.getUsername());
        Entity.setEmail(Dto.getEmail());
        Entity.setUserFIO(Dto.getUserFIO());
        Entity.setPassword(Dto.getPassword());
        byte[] photo = null;
        if(Dto.getPhoto() != null)
            photo = Dto.getPhoto().getBytes();
        Entity.setPhoto(photo);
        Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();
        for (RoleDTO role: Dto.getRoles()) {
            RoleEntity roleEntityEntity = (RoleEntity) toEntity(role);
            roleEntities.add(roleEntityEntity);
        }
        Entity.setRole(roleEntities);

        return Entity;
    }
    public UserDTO userToDto(UserEntity Entity)
    {
        if (Entity == null) return null;
        UserDTO Dto = new UserDTO();

        Dto.setId(Entity.getId());
        Dto.setUsername(Entity.getUsername());
        Dto.setEmail(Entity.getEmail());
        Dto.setUserFIO(Entity.getUserFIO());
        Dto.setPassword(Entity.getPassword());
        String photo = "";
        if(Entity.getPhoto() != null)
            photo = new String(Entity.getPhoto(), StandardCharsets.UTF_8);
        Dto.setPhoto(photo);
        List<RoleDTO> roles = new ArrayList<>();
        for (RoleEntity roleEntity : Entity.getRole()) {
            RoleDTO roleDTO = (RoleDTO) roleToDto(roleEntity);
            roles.add(roleDTO);
        }
        Dto.setRoles(roles);
        return Dto;
    }

    public RoleDTO roleToDto(RoleEntity Entity)
    {
        if (Entity == null) return null;
        RoleDTO Dto = new RoleDTO();
        Dto.setId((int) Entity.getId());
        Dto.setRoleName(Entity.getRoleName());
        return Dto;
    }
    public RoleEntity roleToEntity(RoleDTO Dto)
    {
        if (Dto == null) return null;
        RoleEntity Entity = new RoleEntity();
        Entity.setId(Dto.getId());
        Entity.setRoleName(Dto.getRoleName());
        return Entity;
    }

    public InstituteDTO instituteToDto(InstituteEntity Entity)
    {
        if (Entity == null) return null;
        InstituteDTO Dto = new InstituteDTO();

        Dto.setId(Entity.getId());
        Dto.setName(Entity.getName());
        return Dto;
    }
    public InstituteEntity instituteToEntity(InstituteDTO Dto)
    {
        if (Dto == null) return null;
        InstituteEntity Entity = new InstituteEntity();

        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        return Entity;
    }

    public DepartmentDTO departmentToDto(DepartmentEntity Entity)
    {
        if (Entity == null) return null;
        DepartmentDTO Dto = new DepartmentDTO();

        Dto.setId(Entity.getId());
        Dto.setName(Entity.getName());
        Dto.setInstitute((InstituteDTO) instituteToDto(Entity.getInstitute()));
        return Dto;
    }
    public DepartmentEntity departmentToEntity(DepartmentDTO Dto)
    {
        if (Dto == null) return null;
        DepartmentEntity Entity = new DepartmentEntity();
        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        Entity.setInstitute((InstituteEntity) toEntity(Dto.getInstitute()));
        return Entity;
    }

    public GroupDTO groupToDto(GroupEntity Entity)
    {
        if (Entity == null) return null;
        GroupDTO Dto = new GroupDTO();

        Dto.setName(Entity.getName());
        Dto.setId(Entity.getId());
        Dto.setDepartment((DepartmentDTO) departmentToDto(Entity.getDepartment()));

        return Dto;
    }
    public GroupEntity groupToEntity(GroupDTO Dto)
    {
        if (Dto == null) return null;

        GroupEntity Entity = new GroupEntity();

        Entity.setName(Dto.getName());
        Entity.setId(Dto.getId());
        Entity.setDepartment((DepartmentEntity) toEntity(Dto.getDepartment()));

        return Entity;
    }

    private DisciplineDTO disciplineToDto(DisciplineEntity Entity)
    {
        if (Entity == null) return null;
        DisciplineDTO Dto = new DisciplineDTO();

        Dto.setId((int) Entity.getId());
        Dto.setName(Entity.getName());
        if(Entity.getFieldOfKnowledge() != null)
            Dto.setFieldOfKnowledge((FieldOfKnowledgeDTO) fieldOfKnowledgeToDto(Entity.getFieldOfKnowledge()));

        return Dto;
    }
    private DisciplineEntity disciplineToEntity(DisciplineDTO Dto)
    {
        if (Dto == null) return null;
        DisciplineEntity Entity = new DisciplineEntity();

        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        if (Dto.getFieldOfKnowledge() != null) {
            Entity.setFieldOfKnowledge((FieldOfKnowledgeEntity) toEntity(Dto.getFieldOfKnowledge()));
        }

        return Entity;
    }

    private FieldOfKnowledgeDTO fieldOfKnowledgeToDto(FieldOfKnowledgeEntity Entity)
    {
        if (Entity == null) return null;

        FieldOfKnowledgeDTO Dto = new FieldOfKnowledgeDTO();
        Dto.setName(Entity.getName());
        Dto.setId(Entity.getId());

        return Dto;
    }
    private FieldOfKnowledgeEntity fieldOfKnowledgeToEntity(FieldOfKnowledgeDTO Dto)
    {
        if (Dto == null) return null;

        FieldOfKnowledgeEntity Entity = new FieldOfKnowledgeEntity();

        Entity.setName(Dto.getName());
        Entity.setId(Dto.getId());

        return Entity;
    }

    private FileByteCodeModel fileByteCodeToDto(FileByteCode entity)
    {
        if (entity == null) return null;
        FileByteCodeModel Dto = new FileByteCodeModel();
        Dto.setData(entity.getData());
        Dto.setFileDescription((FileDescriptionModel) fileDescriptionToDto(entity.getFileDescription()));
        Dto.setId(entity.getId());
        return Dto;
    }

    private FileByteCode fileByteCodeToEntity(FileByteCodeModel model)
    {
        if (model == null) return null;
        FileByteCode Entity = new FileByteCode();
        Entity.setData(model.getData());
        Entity.setId(model.getId());
        Entity.setFileDescription((FileDescription) toEntity(model.getFileDescription()));
        return Entity;
    }

    private FileDescriptionModel fileDescriptionToDto(FileDescription entity)
    {
        if (entity == null) return null;
        FileDescriptionModel Dto = new FileDescriptionModel();
        Dto.setFileName(entity.getFileName());
        Dto.setFileType(entity.getFileType());
        Dto.setId(entity.getId());
        return Dto;
    }

    private FileDescription fileDescriptionToEntity(FileDescriptionModel model)
    {
        if (model == null) return null;
        FileDescription Entity = new FileDescription();
        Entity.setFileName(model.getFileName());
        Entity.setFileType(model.getFileType());
        Entity.setId(model.getId());
        return Entity;
    }
}
