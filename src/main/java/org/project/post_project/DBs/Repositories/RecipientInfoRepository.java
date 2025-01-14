package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.RecipientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipientInfoRepository extends JpaRepository<RecipientInfo, Long> {

    Optional<RecipientInfo> findByPhone(String phone);
}