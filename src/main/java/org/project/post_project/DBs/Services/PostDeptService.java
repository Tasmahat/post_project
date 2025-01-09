package org.project.post_project.DBs.Services;

import lombok.extern.slf4j.Slf4j;
import org.project.post_project.DBs.Enities.PostDept;
import org.project.post_project.DBs.Repositories.PostDeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PostDeptService {

    private final PostDeptRepository postDeptRepository;

    @Autowired
    public PostDeptService(PostDeptRepository postDeptRepository) {
        this.postDeptRepository = postDeptRepository;
    }

    public PostDept createPostDept(String index, String region, String city, String address) {
        if (getPostDeptByIndex(index) != null) {
            log.warn("Почтовое отделение уже существует!");
            return null;
        }
        PostDept postDept = new PostDept();
        postDept.setRegion(region);
        postDept.setCity(city);
        postDept.setAddress(address);
        return postDeptRepository.save(postDept);
    }

    public Page<PostDept> getAllPostDept() {
        Pageable pageable = Pageable.unpaged();
        return postDeptRepository.findAll(pageable);
    }

    public PostDept getPostDeptByIndex(String index) {
        return postDeptRepository.findByIndex(index);
    }

    public PostDept updatePostDept(
            String index,
            Optional<String> region,
            Optional<String> city,
            Optional<String> address) {
        PostDept postDept = getPostDeptByIndex(index);
        if (postDept == null) {
            log.warn("Почтовое отделение не существует!");
            return null;
        }
        region.ifPresent(postDept::setRegion);
        city.ifPresent(postDept::setCity);
        address.ifPresent(postDept::setAddress);
        return postDeptRepository.save(postDept);
    }

    public void deletePostDept(String index) {
        PostDept postDept = getPostDeptByIndex(index);
        if (postDept == null) {
            log.warn("Почтовое отделение не существует!");
            return;
        }
        postDeptRepository.delete(postDept);
    }
}
