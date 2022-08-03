package com.exadel.sober.services;

import com.exadel.sober.models.Promise;
import com.exadel.sober.repositories.PromiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromiseService {

    private PromiseRepository promiseRepository;


    public PromiseService(PromiseRepository promiseRepository) {
        this.promiseRepository = promiseRepository;
    }

    public List<Promise> getPromisesForClient(Integer userId) {
        List<Promise> promises = promiseRepository.findAllByUserId(userId);
        promises.forEach(promise -> {
            promise.setReasons(promiseRepository.findAllReasonsByPromiseId(promise.getPromiseId()));
            promise.setName("i will not " + promise.getName());
        });
        return promises;
    };

    public void addNewPromiseByUserId(Promise newPromise) {
        promiseRepository.save(newPromise);
    }

}
