package com.unesco.core.repositories.news;

import com.unesco.core.entities.news.NewsEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends CrudRepository<NewsEntity, Long>, CrudPagableRepository<NewsEntity, Long> {
    NewsEntity findTop1ByOrderByDateDesc();
    NewsEntity findById(long id);
    Page findAll(Pageable pageRequest);

    @Query("SELECT n FROM NewsEntity n where lower(n.header) LIKE CONCAT('%',lower(:filter),'%')")
    List<NewsEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}
