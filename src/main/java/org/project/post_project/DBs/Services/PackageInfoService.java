package org.project.post_project.DBs.Services;

import org.project.post_project.DBs.Enities.PackageInfo;
import org.project.post_project.DBs.Repositories.PackageInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PackageInfoService {

    private final PackageInfoRepository packageInfoRepository;

    @Autowired
    public PackageInfoService(PackageInfoRepository packageInfoRepository) {
        this.packageInfoRepository = packageInfoRepository;
    }

    public Page<PackageInfo> getAllPackageInfo() {
        Pageable pageable = Pageable.unpaged();
        return packageInfoRepository.findAll(pageable);
    }
}
