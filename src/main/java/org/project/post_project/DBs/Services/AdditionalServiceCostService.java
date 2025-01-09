package org.project.post_project.DBs.Services;

import org.project.post_project.DBs.Enities.AdditionalServiceCost;
import org.project.post_project.DBs.Repositories.AdditionalServiceCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdditionalServiceCostService {

    private final AdditionalServiceCostRepository additionalServiceCostRepository;

    @Autowired
    public AdditionalServiceCostService(AdditionalServiceCostRepository additionalServiceCostRepository) {
        this.additionalServiceCostRepository = additionalServiceCostRepository;
    }

    public Page<AdditionalServiceCost> getAllAdditionalServiceCost() {
        Pageable pageable = Pageable.unpaged();
        return additionalServiceCostRepository.findAll(pageable);
    }
}
