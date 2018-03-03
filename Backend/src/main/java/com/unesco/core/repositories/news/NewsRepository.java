package com.unesco.core.repositories.news;

import com.unesco.core.entities.news.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
    News findTop1ByOrderByDateDesc();
    News findById(long id);
}
