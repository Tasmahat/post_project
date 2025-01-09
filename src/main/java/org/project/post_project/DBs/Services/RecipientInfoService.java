package org.project.post_project.DBs.Services;

import org.project.post_project.DBs.Enities.RecipientInfo;
import org.project.post_project.DBs.Repositories.RecipientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipientInfoService {

    private final RecipientInfoRepository recipientInfoRepository;

    @Autowired
    public RecipientInfoService(RecipientInfoRepository recipientInfoRepository) {
        this.recipientInfoRepository = recipientInfoRepository;
    }

    public Page<RecipientInfo> getAllRecipientInfo() {
        Pageable pageable = Pageable.unpaged();
        return recipientInfoRepository.findAll(pageable);
    }
}
