package com.unesco.core.repositories.issue;


import com.unesco.core.entities.workflow.Issue;
import com.unesco.core.entities.account.User;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long>, CrudPagableRepository<Issue, Long> {
    Page findAll(Pageable pageRequest);
    List<Issue> findByCreator(long id);
    List<Issue> findByCollaborators(User user);
    Issue findById(long id);
}