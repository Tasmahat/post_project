package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.PackageInfo;
import org.project.post_project.DBs.Services.PackageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/package")
public class PackageInfoController {

    private final PackageInfoService packageInfoService;

    @Autowired
    public PackageInfoController(PackageInfoService packageInfoService) {
        this.packageInfoService = packageInfoService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<PackageInfo> getAllPackageInfo() {
        return packageInfoService.getAllPackageInfo();
    }
}
