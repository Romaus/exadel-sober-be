package com.exadel.sober.services;

import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.ReasonRepository;
import org.springframework.stereotype.Service;


@Service
public class ReasonService {
    private ReasonRepository reasonRepository;

    public ReasonService(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }

    public void saveReason(Reason newReason) {
        reasonRepository.save(newReason);
    }
}
