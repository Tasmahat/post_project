package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.PostWorkers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostWorkersRepository extends JpaRepository<PostWorkers, Long> {

    Optional<PostWorkers> findByServiceNumber(Long serviceNumber);
}