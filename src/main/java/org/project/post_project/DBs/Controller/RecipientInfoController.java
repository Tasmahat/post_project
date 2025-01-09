package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.RecipientInfo;
import org.project.post_project.DBs.Services.RecipientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipient")
public class RecipientInfoController {

    private final RecipientInfoService recipientInfoService;

    @Autowired
    public RecipientInfoController(RecipientInfoService recipientInfoService) {
        this.recipientInfoService = recipientInfoService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<RecipientInfo> getAllRecipientInfo() {
        return recipientInfoService.getAllRecipientInfo();
    }
}
