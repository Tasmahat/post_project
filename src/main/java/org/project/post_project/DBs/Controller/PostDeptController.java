package org.project.post_project.DBs.Controller;

import org.project.post_project.DBs.Enities.PostDept;
import org.project.post_project.DBs.Services.PostDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/postDept")
public class PostDeptController {

    private final PostDeptService postDeptService;

    @Autowired
    public PostDeptController(PostDeptService postDeptService) {
        this.postDeptService = postDeptService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public PostDept createPostDept(
            @RequestParam String index,
            @RequestParam String region,
            @RequestParam String city,
            @RequestParam String address
    ) {
        return postDeptService.createPostDept(index, region, city, address);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Page<PostDept> getAllPostDept() {
        return postDeptService.getAllPostDept();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public PostDept updatePostDept(
            @RequestParam String index,
            @RequestParam Optional<String> region,
            @RequestParam Optional<String> city,
            @RequestParam Optional<String> address
    ) {
        return postDeptService.updatePostDept(index, region, city, address);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public void deletePostDept(@RequestParam String index) {
        postDeptService.deletePostDept(index);
    }
}
