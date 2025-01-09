package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.Pks.SenderInfoPk;
import org.project.post_project.DBs.Enities.SenderInfo;
import org.project.post_project.DBs.Repositories.SenderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SenderInfoService {

    private final SenderInfoRepository senderInfoRepository;

    @Autowired
    public SenderInfoService(SenderInfoRepository senderInfoRepository) {
        this.senderInfoRepository = senderInfoRepository;
    }

    public SenderInfo createSenderInfo(String fullName, String passport, String phone, String address) {
        SenderInfoPk senderInfoPk = new SenderInfoPk(passport, phone);
        if(getSenderInfoByPk(senderInfoPk) != null) {
            log.warn("Отправитель уже существует!");
        }
        SenderInfo senderInfo = new SenderInfo();
        senderInfo.setSenderInfoPk(senderInfoPk);
        senderInfo.setFullName(fullName);
        senderInfo.setAddress(address);
        return senderInfoRepository.save(senderInfo);
    }

    public Page<SenderInfo> getAllSenderInfo() {
        Pageable pageable = Pageable.unpaged();
        return senderInfoRepository.findAll(pageable);
    }

    public SenderInfo getSenderInfoByPk(SenderInfoPk senderInfoPk) {
        return senderInfoRepository.findBySenderInfoPk(senderInfoPk);
    }
}
