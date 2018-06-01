package com.unesco.core.repositories.news;

import com.unesco.core.entities.news.News;
import com.unesco.core.entities.schedule.Room;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long>, CrudPagableRepository<News, Long> {
    News findTop1ByOrderByDateDesc();
    News findById(long id);
    Page findAll(Pageable pageRequest);

    @Query("SELECT n FROM News n where lower(n.header) LIKE CONCAT('%',lower(:filter),'%')")
    List<News> findWithFilter(Pageable pageable, @Param("filter")  String filter);
}
