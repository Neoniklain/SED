package com.unesco.core.repositories;

import com.unesco.core.entities.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    News findTop1ByOrderByDateDesc();
}
