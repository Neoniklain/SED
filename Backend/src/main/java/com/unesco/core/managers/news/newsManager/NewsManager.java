package com.unesco.core.managers.news.newsManager;

import com.unesco.core.managers.news.newsManager.interfaces.news.INewsManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.utils.StatusTypes;
import org.springframework.stereotype.Service;

@Service
public class NewsManager implements INewsManager {

    public NewsDTO news;

    public NewsManager() {
        news = new NewsDTO();
    }

    public void Init(NewsDTO model) {
        news = model;
    }

    public NewsDTO Get() {
        return news;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (news.getDate() == null) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана дата");
        }
        if (news.getAuthor().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан автор");
        }
        if (news.getContent().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Отсутствует тело новости.");
        }
        if (news.getHeader().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан заголовок.");
        }
        return responseStatusDTO;
    }
}
