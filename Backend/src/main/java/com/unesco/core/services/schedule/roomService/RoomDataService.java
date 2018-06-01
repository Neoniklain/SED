package com.unesco.core.services.schedule.roomService;

import com.unesco.core.entities.schedule.Room;
import com.unesco.core.models.shedule.RoomModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.repositories.RoomRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomDataService implements IRoomDataService {

    @Autowired
    private IMapperService mapperService;
    @Autowired
    private RoomRepository roomRepository;

    public List<RoomModel> GetPage(FilterQuery filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) roomRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<Room> entitys = roomRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<RoomModel> result = new ArrayList<RoomModel>();
        for (Room e: entitys) {
            result.add((RoomModel) mapperService.toModel(e));
        }
        return result;
    }

    public List<RoomModel> GetAll()
    {
        List<RoomModel> modelList = new ArrayList<>();
        Iterable<Room> entityList = roomRepository.findAll();
        for (Room item: entityList) {
            RoomModel model = (RoomModel) mapperService.toModel(item);
            modelList.add(model);
        }
        return modelList;
    }

    public RoomModel Get(long id)
    {
        Room entity = roomRepository.findOne(id);
        RoomModel model = (RoomModel) mapperService.toModel(entity);
        return model;
    }

    public void Delete(long id)
    {
        roomRepository.delete(id);
    }

    public RoomModel Save(RoomModel room)
    {
        Room entity = (Room) mapperService.toEntity(room);
        Room model = roomRepository.save(entity);
        room = (RoomModel) mapperService.toModel(model);
        return room;
    }
}
