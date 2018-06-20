package com.unesco.core.managers.news.newsManager;

import com.unesco.core.managers.news.newsManager.interfaces.news.INewsManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.news.NewsModel;
import com.unesco.core.utils.StatusTypes;
import org.springframework.stereotype.Service;

@Service
public class NewsManager implements INewsManager {

    public NewsModel news;

    public NewsManager() {
        news = new NewsModel();
    }

    public void Init(NewsModel model) {
        news = model;
    }

    public NewsModel Get() {
        return news;
    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (news.getDate() == null) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указана дата");
        }
        if (news.getAuthor().getId() == 0) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан автор");
        }
        if (news.getContent().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Отсутствует тело новости.");
        }
        if (news.getHeader().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан заголовок.");
        }
        return responseStatus;
    }
}
