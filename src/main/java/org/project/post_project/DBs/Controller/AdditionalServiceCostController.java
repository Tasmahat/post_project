package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.AdditionalServiceCost;
import org.project.post_project.DBs.Services.AdditionalServiceCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/additionalService")
public class AdditionalServiceCostController {

    private final AdditionalServiceCostService additionalServiceCostService;

    @Autowired
    public AdditionalServiceCostController(AdditionalServiceCostService additionalServiceCostService) {
        this.additionalServiceCostService = additionalServiceCostService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<AdditionalServiceCost> getAllAdditionalServiceCost() {
        return additionalServiceCostService.getAllAdditionalServiceCost();
    }
}
