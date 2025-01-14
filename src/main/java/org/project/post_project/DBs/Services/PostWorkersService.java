package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.PostWorkers;
import org.project.post_project.DBs.Repositories.PostWorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@Slf4j
public class PostWorkersService {

    private final PostWorkersRepository postWorkersRepository;

    @Autowired
    public PostWorkersService(PostWorkersRepository postWorkersRepository) {
        this.postWorkersRepository = postWorkersRepository;
    }

    public PostWorkers createPostWorker(Long serviceNumber, String position, String fullName) {
        if (getPostWorker(serviceNumber) != null) {
            log.warn("Такой работник уже существует!");
            return null;
        }
        PostWorkers postWorker = new PostWorkers();
        postWorker.setServiceNumber(serviceNumber);
        postWorker.setPosition(position);
        postWorker.setFullName(fullName);
        return postWorkersRepository.save(postWorker);
    }

    public Page<PostWorkers> getAllPostWorkers() {
        Pageable pageable = Pageable.unpaged();
        return postWorkersRepository.findAll(pageable);
    }

    public PostWorkers getPostWorker(Long serviceNumber) {
        return postWorkersRepository.findByServiceNumber(serviceNumber).orElse(null);
    }

    public PostWorkers updatePostWorker(
            @RequestParam Long serviceNumber,
            @RequestParam Optional<String> position,
            @RequestParam Optional<String> fullName
    ) {
        PostWorkers postWorker = getPostWorker(serviceNumber);
        if (postWorker == null) {
            log.warn("Такого работника не существует!");
            return null;
        }
        position.ifPresent(postWorker::setPosition);
        fullName.ifPresent(postWorker::setFullName);
        return postWorkersRepository.save(postWorker);
    }

    public void deletePostWorker(Long serviceNumber) {
        PostWorkers postWorker = getPostWorker(serviceNumber);
        if (postWorker == null) {
            log.warn("Такого работника не существует!");
            return;
        }
        postWorkersRepository.delete(postWorker);
    }
}
