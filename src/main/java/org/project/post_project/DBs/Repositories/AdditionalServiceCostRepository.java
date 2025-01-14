package org.project.post_project.DBs.Repositories;

import org.project.post_project.DBs.Enities.AdditionalServiceCost;
import org.project.post_project.DBs.Enities.Pks.AdditionalServiceCostPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdditionalServiceCostRepository extends JpaRepository<AdditionalServiceCost, Long> {

    Optional<AdditionalServiceCost> findByAdditionalServiceCostPk(AdditionalServiceCostPk additionalServiceCostPk);
}