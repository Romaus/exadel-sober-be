package com.exadel.sober.services;

import com.exadel.sober.exceptions.CannotAddPromiseException;
import com.exadel.sober.exceptions.CannotAddReasonException;
import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.PromiseRepository;
import org.springframework.stereotype.Service;
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

    public List<Promise> addNewPromiseByUserId(Promise newPromise) {
        try {
            promiseRepository.save(newPromise);
        }
        catch (Exception ex) {
            throw new CannotAddPromiseException("Smth is going wrong, you sent bad Promise");
        }
        return getPromisesForClient(newPromise.getUserId());
    }

    public List<Promise> addNewReasonForPromise(Reason newReason) {
        try {
            reasonService.saveReason(newReason);
        }
        catch (Exception ex) {
            throw new CannotAddReasonException("Smth is going wrong, you sent bad Reason");
        }
        Integer userId = promiseRepository.findPromiseByPromiseId(newReason.getPromiseId()).getUserId();
        return getPromisesForClient(userId);
    }

    public List<Promise> deletePromise(Integer promiseId) {
        Integer userId = promiseRepository.findPromiseByPromiseId(promiseId).getUserId();
        promiseRepository.deletePromiseByPromiseId(promiseId);
        return getPromisesForClient(userId);
    }

}
