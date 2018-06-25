package com.unesco.core.services.schedule.roomService;

import com.unesco.core.entities.schedule.RoomEntity;
import com.unesco.core.dto.shedule.RoomDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
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

    public List<RoomDTO> GetPage(FilterQueryDTO filter) {
        int rows = filter.getRows()>0? filter.getRows() : (int) roomRepository.count();
        int start = rows>0 ? filter.getFirst()/rows: 1;
        List<RoomEntity> entitys = roomRepository.findWithFilter(new PageRequest(start, rows == 0 ? 10 : rows), filter.getGlobalFilter());
        List<RoomDTO> result = new ArrayList<RoomDTO>();
        for (RoomEntity e: entitys) {
            result.add((RoomDTO) mapperService.toDto(e));
        }
        return result;
    }

    public List<RoomDTO> GetAll()
    {
        List<RoomDTO> modelList = new ArrayList<>();
        Iterable<RoomEntity> entityList = roomRepository.findAll();
        for (RoomEntity item: entityList) {
            RoomDTO model = (RoomDTO) mapperService.toDto(item);
            modelList.add(model);
        }
        return modelList;
    }

    public RoomDTO Get(long id)
    {
        RoomEntity entity = roomRepository.findOne(id);
        RoomDTO model = (RoomDTO) mapperService.toDto(entity);
        return model;
    }

    public RoomDTO GetByRoom(String room)
    {
        RoomEntity entity = roomRepository.findByRoom(room);
        RoomDTO model = (RoomDTO) mapperService.toDto(entity);
        return model;
    }

    public void Delete(long id)
    {
        roomRepository.delete(id);
    }

    public RoomDTO Save(RoomDTO room)
    {
        RoomEntity entity = (RoomEntity) mapperService.toEntity(room);
        RoomEntity model = roomRepository.save(entity);
        room = (RoomDTO) mapperService.toDto(model);
        return room;
    }
}
