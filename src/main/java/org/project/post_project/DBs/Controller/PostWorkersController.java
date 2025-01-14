package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.PostWorkers;
import org.project.post_project.DBs.Services.PostWorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/workers")
public class PostWorkersController {

    private final PostWorkersService postWorkersService;

    @Autowired
    public PostWorkersController(PostWorkersService postWorkersService) {
        this.postWorkersService = postWorkersService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public PostWorkers createPostWorker(
            @RequestParam Long serviceNumber,
            @RequestParam String position,
            @RequestParam String fullName
    ) {
        return postWorkersService.createPostWorker(serviceNumber, position, fullName);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<PostWorkers> getAllPostWorkers() {
        return postWorkersService.getAllPostWorkers();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public PostWorkers updatePostWorker(
            @RequestParam Long serviceNumber,
            @RequestParam Optional<String> position,
            @RequestParam Optional<String> fullName
    ) {
        return postWorkersService.updatePostWorker(serviceNumber, position, fullName);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void deletePostWorker(@RequestParam Long serviceNumber) {
        postWorkersService.deletePostWorker(serviceNumber);
    }
}
