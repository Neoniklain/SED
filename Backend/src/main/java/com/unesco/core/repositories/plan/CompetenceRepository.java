package com.unesco.core.repositories.plan;

import com.unesco.core.entities.news.News;
import com.unesco.core.entities.plan.Competence;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CompetenceRepository extends CrudRepository<Competence, Long>, CrudPagableRepository<Competence, Long> {
    Competence findByCode(String code);
}
