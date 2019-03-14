package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.managers.moodle.interfaces.IMoodleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodleController {

    @Autowired
    IMoodleManager _moodle;

    public ResponseStatusDTO GetAllCourses() {
        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setStatus(StatusTypes.ERROR);
        try{
            result.setData(_moodle.GetAllCourses());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO GetAllUsers() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.GetAllUsers());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setStatus(StatusTypes.ERROR);
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO GetUserById(long userId) {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.GetUserById(userId));
            if(result.getData() == null) {
                result.addWarning("Пользователь не найден, или найдено больше чем 1 пользователей.");
            }
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setStatus(StatusTypes.ERROR);
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO GetAllGroups() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.GetAllGroups());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
            result.setStatus(StatusTypes.ERROR);
        }
        return result;
    }

    public ResponseStatusDTO CreateGroups() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateGroups());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            result.setStatus(StatusTypes.ERROR);
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateCourses() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateCourses());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateStudents(long groupId) {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateStudents(groupId));
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateUser(long userId) {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateUser(userId));
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateAllStudents() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateAllStudents());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            result.setStatus(StatusTypes.ERROR);
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO AssignStudentsOnGroups() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setStatus(StatusTypes.OK);
            result.setData(_moodle.AllAssignStudentsOnGroups());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setStatus(StatusTypes.ERROR);
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO AssignStudentsOnGroups(long groupId) {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setStatus(StatusTypes.OK);
            result.setData(_moodle.AssignStudentsOnGroups(groupId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setStatus(StatusTypes.ERROR);
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }
}
