package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.RecipientInfo;
import org.project.post_project.DBs.Repositories.RecipientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@Slf4j
public class RecipientInfoService {

    private final RecipientInfoRepository recipientInfoRepository;

    @Autowired
    public RecipientInfoService(RecipientInfoRepository recipientInfoRepository) {
        this.recipientInfoRepository = recipientInfoRepository;
    }

    public RecipientInfo createRecipientInfo(String fullName, String phone, String address) {
        if (getRecipientInfo(phone) != null) {
            log.warn("Такой получатель уже существует!");
            return null;
        }
        RecipientInfo recipientInfo = new RecipientInfo();
        recipientInfo.setPhone(phone);
        recipientInfo.setFullName(fullName);
        recipientInfo.setAddress(address);
        return recipientInfoRepository.save(recipientInfo);
    }

    public Page<RecipientInfo> getAllRecipientInfo() {
        Pageable pageable = Pageable.unpaged();
        return recipientInfoRepository.findAll(pageable);
    }

    public RecipientInfo getRecipientInfo(String phone) {
        return recipientInfoRepository.findByPhone(phone).orElse(null);
    }

    public RecipientInfo updateRecipientInfo(Optional<String> fullName, String phone, Optional<String> address) {
        RecipientInfo recipientInfo = getRecipientInfo(phone);
        if (recipientInfo == null) {
            log.warn("Такого получателя не существует!");
            return null;
        }
        fullName.ifPresent(recipientInfo::setFullName);
        address.ifPresent(recipientInfo::setAddress);
        return recipientInfoRepository.save(recipientInfo);
    }

    public void deleteRecipientInfo(String phone) {
        RecipientInfo recipientInfo = getRecipientInfo(phone);
        if (recipientInfo == null) {
            log.warn("Такого получателя не существует!");
            return;
        }
        recipientInfoRepository.delete(recipientInfo);
    }
}
