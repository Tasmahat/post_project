package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;
import org.project.post_project.DBs.Enities.SenderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SenderInfoRepository extends JpaRepository<SenderInfo, Long> {

    Optional<SenderInfo> findBySenderInfoPk(SenderInfoPk senderInfoPk);
}
