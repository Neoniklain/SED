package com.unesco.core.repositories.news;

import com.unesco.core.entities.news.News;
import com.unesco.core.entities.workflow.Issue;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long>, CrudPagableRepository<News, Long> {
    News findTop1ByOrderByDateDesc();
    News findById(long id);
    Page findAll(Pageable pageRequest);
}
