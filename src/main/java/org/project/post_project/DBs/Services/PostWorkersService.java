package org.project.post_project.DBs.Services;

import org.project.post_project.DBs.Enities.PostWorkers;
import org.project.post_project.DBs.Repositories.PostWorkersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostWorkersService {

    private final PostWorkersRepository postWorkersRepository;

    @Autowired
    public PostWorkersService(PostWorkersRepository postWorkersRepository) {
        this.postWorkersRepository = postWorkersRepository;
    }

    public Page<PostWorkers> getAllPostWorkers() {
        Pageable pageable = Pageable.unpaged();
        return postWorkersRepository.findAll(pageable);
    }
}
