package com.unesco.core.repositories;


import com.unesco.core.entities.Issue;
import com.unesco.core.entities.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    List<Issue> findByCreator(long id);
    Issue findById(long id);
}