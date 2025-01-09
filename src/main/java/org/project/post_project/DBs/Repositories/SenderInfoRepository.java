package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;
import org.project.post_project.DBs.Enities.SenderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenderInfoRepository extends JpaRepository<SenderInfo, Long> {

    SenderInfo findBySenderInfoPk(SenderInfoPk senderInfoPk);
}
