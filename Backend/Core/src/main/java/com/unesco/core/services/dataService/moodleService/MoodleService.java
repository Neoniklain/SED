package com.unesco.core.services.dataService.moodleService;

import com.unesco.core.config.MoodleConfig.IMoodleConfigService;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.repositories.moodlerest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MoodleService implements IMoodleService {

    @Autowired
    private IMoodleConfigService _moodleConfigService;

    MoodleService(IMoodleConfigService moodleConfigService) {
        _moodleConfigService = moodleConfigService;
        this.Init();
    }

    private void Init() {
        // Токкен авторизации, для доступа к API Moodle
        MoodleCallRestWebService.setAuth(_moodleConfigService.GetToken());
        // Адрес Moodle
        MoodleCallRestWebService.setURL(_moodleConfigService.GetURL());
    }

    public MoodleCourse[] GetAllCourses() {
        MoodleCourse[] result = null;
        try {
            result = MoodleRestCourse.getAllCourses();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleUser[] GetAllUsers() {
        MoodleUser[] result = null;
        try {
            Criteria[] criteria = new Criteria[1];
            criteria[0] = new Criteria("email", "%%");
            result = MoodleRestUser.getUsers(criteria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleUser GetUserByEmail(String email) {
        MoodleUser result = null;
        try {
            Criteria[] criteria = new Criteria[1];
            criteria[0] = new Criteria("email", email);
            MoodleUser[] resultArray = MoodleRestUser.getUsers(criteria);
            if (resultArray != null) {
                if (resultArray.length == 1) {
                    result = resultArray[0];
                } else {
                    result = null;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleCohort[] GetAllGroups() {
        MoodleCohort[] result = null;
        try {
            Long[] CohortIds = new Long[0];
            result = MoodleRestCohort.getCohorts(CohortIds);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleCourse[] CreateCourses() {
        MoodleCourse[] result = null;
        try {
            // 12 - id Иванова
            /*List<PairDTO> pairs = pairDataService.getAllByProfessor(12);
            List<DisciplineDTO> discs = new ArrayList<>();
            for (PairDTO pair: pairs) {
                Optional<DisciplineDTO> matchingObject = discs.stream().
                        filter(p -> p.getId() == pair.getLesson().getDiscipline().getId()).
                        findFirst();
                if(!matchingObject.isPresent()) {
                    discs.add(pair.getLesson().getDiscipline());
                }
            }*/

            MoodleCourse[] allCourses = this.GetAllCourses();
            if (allCourses == null) {
                return result;
            }

            List<MoodleCourse> listCourses = new ArrayList<>(Arrays.asList(allCourses));

            try {
                List<MoodleCourse> courses = new ArrayList<>();
                Long catId = Long.valueOf(2);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date startDate = dateFormat.parse("01/09/2018");
                long start = startDate.getTime();
                // Убираем три нуля в концеъ
                start = start / 1000;
                // 1546210800 - пример корректной даты

                Date endDate = dateFormat.parse("30/12/2018");
                long end = endDate.getTime();
                // Убираем три нуля в концеъ
                end = end / 1000;
                // 1546210800 - пример корректной даты

                /*for (DisciplineDTO disc: forCreate) {
                    MoodleCourse course = new MoodleCourse();
                    course.setFullname(disc.getName());
                    course.setShortname(disc.getName());
                    course.setCategoryId(catId);
                    course.setStartDate(start);
                    course.setEndDate(end);
                    course.setNumSections(1);
                    course.setVisible(true);
                    courses.add(course);
                }*/

                if (courses.size() > 0) {
                    MoodleCourse[] forCreateCourses = courses.toArray(new MoodleCourse[courses.size()]);
                    // result = MoodleRestCourse.createCourses(forCreateCourses);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result = null;
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public MoodleUser[] CreateUsers(List<UserDTO> users, boolean LineByLineCreation) {
        MoodleUser[] result = null;
        try {
            List<UserDTO> notCreatedUsers = new ArrayList<>();
            for (UserDTO user : users) {
                MoodleUser mUser = GetUserByEmail(user.getEmail());
                if (mUser == null) {
                    notCreatedUsers.add(user);
                }
            }

            List<MoodleUser> forCreateUsersList = new ArrayList<>();
            for (UserDTO userDTO : notCreatedUsers) {
                MoodleUser user = new MoodleUser();
                user.setFullname(userDTO.getUserFIO());
                user.setFirstname(this.GetFirstName(userDTO.getUserFIO()));
                user.setLastname(this.GetLastName(userDTO.getUserFIO()));
                user.setEmail(userDTO.getEmail());
                user.setPassword("Default!3000");
                user.setUsername(userDTO.getUsername().toLowerCase());
                forCreateUsersList.add(user);
            }

            if (forCreateUsersList.size() > 0) {
                if (LineByLineCreation) {
                    // ↓ Построчное создание
                    List<MoodleUser> resultList = new ArrayList<>();
                    for (MoodleUser forCreate : forCreateUsersList) {
                        try {
                            resultList.add(MoodleRestUser.createUser(forCreate));
                            System.out.println(forCreate.getFullname());
                            System.out.println("Успешно создан(а) в Moodle");
                            System.out.println("-----------------------------------------");
                        } catch (Exception e) {
                            System.out.println(forCreate.getFullname());
                            System.out.println(e.getMessage());
                            System.out.println("-----------------------------------------");
                        }
                    }
                    result = resultList.toArray(new MoodleUser[resultList.size()]);
                    // ↑ Построчное создание
                } else {
                    MoodleUser[] forCreateUsersArray = forCreateUsersList.toArray(new MoodleUser[forCreateUsersList.size()]);
                    result = MoodleRestUser.createUsers(forCreateUsersArray);
                }
            } else {
                result = new MoodleUser[0];
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public MoodleUser[] CreateUsers(List<UserDTO> users) {
        return CreateUsers(users, false);
    }

    public MoodleUser CreateUser(UserDTO user) {
        List<UserDTO> forCreate = new ArrayList<>();
        forCreate.add(user);
        MoodleUser[] res = CreateUsers(forCreate);
        if (res != null) {
            if (res.length > 0) {
                return res[0];
            }
        }
        return null;
    }

    public MoodleUser[] CreateStudents(List<StudentDTO> students, boolean LineByLineCreation) {
        List<UserDTO> forCreate = new ArrayList<>();
        for (StudentDTO student : students) {
            forCreate.add(student.getUser());
        }
        MoodleUser[] created = CreateUsers(forCreate);
        if (created != null) {
            if (created.length > 0) {
                AddUsersToCohorts(students);
            }
        }
        return created;
    }

    public MoodleUser[] CreateStudents(List<StudentDTO> students) {
        return CreateStudents(students, false);
    }

    public boolean AddUsersToCohorts(List<StudentDTO> students, boolean LineByLineCreation) {
        boolean result = false;
        try {

            if (students == null) {
                return false;
            }
            MoodleCohort[] allCohortsFromMoodle = this.GetAllGroups();
            if (allCohortsFromMoodle == null) {
                return false;
            }
            List<MoodleCohort> moodleCohorts = Arrays.asList(allCohortsFromMoodle);

            List<CohortMember> forAddUsersToCohorts = new ArrayList<>();
            for (StudentDTO student : students) {
                Optional<MoodleCohort> cohortExist = moodleCohorts.stream()
                        .filter(p -> p.getName().toUpperCase().equals(student.getGroup().getName().toUpperCase()))
                        .findFirst();
                if (cohortExist.isPresent()) {
                    MoodleUser mUser = GetUserByEmail(student.getUser().getEmail());
                    if (mUser != null) {
                        CohortMember cm = new CohortMember();
                        cm.setCohortTypeId(CohortTypeId.ID_NUMBER);
                        cm.setCohortIdValue(cohortExist.get().getIdNumber());
                        cm.setUserTypeId(UserTypeId.USERNAME);
                        cm.setUserIdValue(student.getUser().getUsername());
                        forAddUsersToCohorts.add(cm);
                    }
                }
            }

            if (forAddUsersToCohorts.size() > 0) {
                if (LineByLineCreation) {
                    // ↓ Построчное создание
                    for (CohortMember forCreate : forAddUsersToCohorts) {
                        try {
                            MoodleRestCohort.addCohortMember(forCreate);
                            System.out.println(forCreate.getUserIdValue());
                            System.out.println("Успешно привязан к группе в Moodle");
                            System.out.println(forCreate.getCohortIdValue());
                            System.out.println("-----------------------------------------");
                        } catch (Exception e) {
                            System.out.println(forCreate.getUserIdValue());
                            System.out.println(e.getMessage());
                            System.out.println("-----------------------------------------");
                        }
                    }
                    // ↑ Построчное создание
                } else {
                    CohortMember[] forCM = forAddUsersToCohorts.toArray(new CohortMember[forAddUsersToCohorts.size()]);
                    MoodleRestCohort.addCohortMembers(forCM);
                }
            }

            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public boolean AddUsersToCohorts(List<StudentDTO> students) {
        return AddUsersToCohorts(students, false);
    }

    public MoodleCohort[] CreateGroups(List<GroupDTO> groups, boolean LineByLineCreation) {
        MoodleCohort[] result = null;
        try {
            MoodleCohort[] allGroupsFromMoodleArray = this.GetAllGroups();
            List<GroupDTO> notCreatedGroups = new ArrayList<>();
            if (allGroupsFromMoodleArray != null) {
                List<MoodleCohort> allGroupsFromMoodle = Arrays.asList(allGroupsFromMoodleArray);
                for (GroupDTO group : groups) {
                    Optional<MoodleCohort> matchingObject = allGroupsFromMoodle.stream()
                            .filter(p -> group.getName().toUpperCase().equals(p.getName().toUpperCase()))
                            .findFirst();
                    if (!matchingObject.isPresent()) {
                        notCreatedGroups.add(group);
                    }
                }
            } else {
                notCreatedGroups = groups;
            }

            List<MoodleCohort> forCreateCohortList = new ArrayList<>();
            for (GroupDTO group : notCreatedGroups) {
                MoodleCohort cohort = new MoodleCohort();
                cohort.setName(group.getName());
                cohort.setIdNumber(group.getName());
                forCreateCohortList.add(cohort);
            }

            if (forCreateCohortList.size() > 0) {
                if (LineByLineCreation) {
                    // ↓ Построчное создание
                    List<MoodleCohort> resultList = new ArrayList<>();
                    for (MoodleCohort forCreate : forCreateCohortList) {
                        try {
                            resultList.add(MoodleRestCohort.createCohort(forCreate));
                            System.out.println(forCreate.getName());
                            System.out.println("Успешно создана в Moodle");
                            System.out.println("-----------------------------------------");
                        } catch (Exception e) {
                            System.out.println(forCreate.getName());
                            System.out.println(e.getMessage());
                            System.out.println("-----------------------------------------");
                        }
                    }
                    result = resultList.toArray(new MoodleCohort[resultList.size()]);
                    // ↑ Построчное создание
                } else {
                    MoodleCohort[] forCreateCohortArray = forCreateCohortList.toArray(new MoodleCohort[forCreateCohortList.size()]);
                    result = MoodleRestCohort.createCohorts(forCreateCohortArray);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public MoodleCohort[] CreateGroups(List<GroupDTO> groups) {
        return CreateGroups(groups, false);
    }

    private String GetFirstName(String fullname) {
        String result = "";
        try {
            fullname = fullname.trim().replaceAll(" +", " ");
            String[] words = fullname.split(" ");
            if (words.length >= 2) {
                result = words[1];
            }
            result = result.replaceAll("\\P{L}+", "");
        } catch (Exception ex) {
            result = "";
        }
        return result;
    }

    private String GetLastName(String fullname) {
        String result = "";
        try {
            fullname = fullname.trim().replaceAll(" +", " ");
            String[] words = fullname.split(" ");
            if (words.length > 0) {
                result = words[0];
            }
            result = result.replaceAll("\\P{L}+", "");
        } catch (Exception ex) {
            result = "";
        }
        return result;
    }

    private String GetMiddleName(String fullname) {
        String result = "";
        try {
            fullname = fullname.trim().replaceAll(" +", " ");
            String[] words = fullname.split(" ");
            if (words.length >= 3) {
                result = words[2];
            }
            result = result.replaceAll("\\P{L}+", "");
        } catch (Exception ex) {
            result = "";
        }
        return result;
    }
}
