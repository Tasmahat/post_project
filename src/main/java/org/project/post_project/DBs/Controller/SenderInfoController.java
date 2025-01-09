package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.SenderInfo;
import org.project.post_project.DBs.Services.SenderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sender")
public class SenderInfoController {

    private final SenderInfoService senderInfoService;

    @Autowired
    public SenderInfoController(SenderInfoService senderInfoService) {
        this.senderInfoService = senderInfoService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public SenderInfo createSenderInfo(
            @RequestParam String fullName,
            @RequestParam String passport,
            @RequestParam String phone,
            @RequestParam String address
    ) {
        return senderInfoService.createSenderInfo(fullName, passport, phone, address);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<SenderInfo> getAllSenderInfo() {
        return senderInfoService.getAllSenderInfo();
    }
}
