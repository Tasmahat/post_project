package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.AdditionalServiceCost;
import org.project.post_project.DBs.Enities.Pks.AdditionalServiceCostPk;
import org.project.post_project.DBs.Repositories.AdditionalServiceCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Slf4j
public class AdditionalServiceCostService {

    private final AdditionalServiceCostRepository additionalServiceCostRepository;

    @Autowired
    public AdditionalServiceCostService(AdditionalServiceCostRepository additionalServiceCostRepository) {
        this.additionalServiceCostRepository = additionalServiceCostRepository;
    }

    public AdditionalServiceCost createAdditionalServiceCost(
            Boolean isToDoor,
            Boolean isFragile,
            Boolean hasInsurance
    ) {
        AdditionalServiceCostPk additionalServiceCostPk
                = new AdditionalServiceCostPk(isToDoor, isFragile, hasInsurance);
        return createAdditionalServiceCost(additionalServiceCostPk);
    }

    public AdditionalServiceCost createAdditionalServiceCost(AdditionalServiceCostPk additionalServiceCostPk) {
        if (getAdditionalServiceCost(additionalServiceCostPk) != null) {
            log.warn("Такая дополнительная стоимость доставки уже существует!");
            return null;
        }
        AdditionalServiceCost additionalServiceCost = new AdditionalServiceCost();
        additionalServiceCost.setAdditionalServiceCostPk(additionalServiceCostPk);
        additionalServiceCost.setCost(calculateAdditionalServiceCost(additionalServiceCostPk));
        return additionalServiceCostRepository.save(additionalServiceCost);
    }

    public Page<AdditionalServiceCost> getAllAdditionalServiceCost() {
        Pageable pageable = Pageable.unpaged();
        return additionalServiceCostRepository.findAll(pageable);
    }

    public AdditionalServiceCost getAdditionalServiceCost(AdditionalServiceCostPk additionalServiceCostPk) {
        return additionalServiceCostRepository.findByAdditionalServiceCostPk(additionalServiceCostPk).orElse(null);
    }

    public void deleteAdditionalServiceCost(
            Boolean isToDoor,
            Boolean isFragile,
            Boolean hasInsurance
    ) {
        AdditionalServiceCostPk additionalServiceCostPk
                = new AdditionalServiceCostPk(isToDoor, isFragile, hasInsurance);
        AdditionalServiceCost additionalServiceCost = getAdditionalServiceCost(additionalServiceCostPk);
        if (additionalServiceCost == null) {
            log.warn("Такая дополнительная стоимость доставки не существует!");
            return;
        }
        additionalServiceCostRepository.delete(additionalServiceCost);
    }

    private Integer calculateAdditionalServiceCost(AdditionalServiceCostPk additionalServiceCostPk) {
        Boolean isToDoor = additionalServiceCostPk.getIsToDoor();
        Boolean isFragile = additionalServiceCostPk.getIsFragile();
        Boolean hasInsurance = additionalServiceCostPk.getHasInsurance();

        Integer cost = 0;
        if (Boolean.TRUE.equals(isToDoor)) {
            cost += 200;
        }
        if (Boolean.TRUE.equals(isFragile)) {
            cost += 100;
        }
        if (Boolean.TRUE.equals(hasInsurance)) {
            cost += 100;
        }
        return cost;
    }
}
