package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.PostWorkers;
import org.project.post_project.DBs.Services.PostWorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workers")
public class PostWorkersController {

    private final PostWorkersService postWorkersService;

    @Autowired
    public PostWorkersController(PostWorkersService postWorkersService) {
        this.postWorkersService = postWorkersService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<PostWorkers> getAllPostWorkers() {
        return postWorkersService.getAllPostWorkers();
    }
}
