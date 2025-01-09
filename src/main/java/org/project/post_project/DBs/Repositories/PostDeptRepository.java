package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.PostDept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDeptRepository extends JpaRepository<PostDept, Long> {

    PostDept findByIndex(String index);
}
