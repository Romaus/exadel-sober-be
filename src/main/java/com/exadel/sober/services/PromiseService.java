package com.exadel.sober.services;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.PromiseRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class PromiseService {
    private PromiseRepository promiseRepository;
    private ReasonService reasonService;


    public PromiseService(PromiseRepository promiseRepository, ReasonService reasonService) {
        this.reasonService = reasonService;
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

    public List<Promise> addNewPromiseByUserId(Integer userId, Integer addictionId, Integer days) {
        Promise p = new Promise();
        p.setUserId(userId);
        p.setAddictionId(addictionId);
        p.setExpiredDate(new Timestamp(System.currentTimeMillis() + days*24*3600*1000));
        promiseRepository.save(p);
        return getPromisesForClient(userId);
    }

    public List<Promise> addNewReasonForPromise(Reason newReason) {
        reasonService.saveReason(newReason);
        Integer userId = promiseRepository.findPromiseByPromiseId(newReason.getPromiseId()).getUserId();
        return getPromisesForClient(userId);
    }

    public List<Promise> deletePromise(Integer promiseId) {
        Integer userId = promiseRepository.findPromiseByPromiseId(promiseId).getUserId();
        promiseRepository.deletePromiseByPromiseId(promiseId);
        return getPromisesForClient(userId);
    }

}
