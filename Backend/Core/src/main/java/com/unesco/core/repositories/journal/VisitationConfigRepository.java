package com.unesco.core.repositories.journal;

import com.unesco.core.entities.journal.VisitationConfigEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.repository.CrudRepository;

public interface VisitationConfigRepository extends CrudRepository<VisitationConfigEntity, Long>, CrudPagableRepository<VisitationConfigEntity, Long> {

    VisitationConfigEntity findByLessonId(long lessonId);

}
