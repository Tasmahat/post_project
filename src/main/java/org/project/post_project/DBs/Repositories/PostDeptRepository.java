package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.PostDept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostDeptRepository extends JpaRepository<PostDept, Long> {

    Optional<PostDept> findByIndex(String index);
}
