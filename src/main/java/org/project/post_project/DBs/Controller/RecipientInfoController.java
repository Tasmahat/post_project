package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.RecipientInfo;
import org.project.post_project.DBs.Services.RecipientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/recipient")
public class RecipientInfoController {

    private final RecipientInfoService recipientInfoService;

    @Autowired
    public RecipientInfoController(RecipientInfoService recipientInfoService) {
        this.recipientInfoService = recipientInfoService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public RecipientInfo createRecipientInfo(
            @RequestParam String fullName,
            @RequestParam String phone,
            @RequestParam String address
    ) {
        return recipientInfoService.createRecipientInfo(fullName, phone, address);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<RecipientInfo> getAllRecipientInfo() {
        return recipientInfoService.getAllRecipientInfo();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public RecipientInfo updateRecipientInfo(
            @RequestParam Optional<String> fullName,
            @RequestParam String phone,
            @RequestParam Optional<String> address
    ) {
        return recipientInfoService.updateRecipientInfo(fullName, phone, address);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void deleteRecipientInfo(@RequestParam String phone) {
        recipientInfoService.deleteRecipientInfo(phone);
    }
}
