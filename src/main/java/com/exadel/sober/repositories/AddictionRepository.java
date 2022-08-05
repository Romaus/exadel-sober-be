package com.exadel.sober.repositories;

import com.exadel.sober.models.Addiction;
import java.util.List;

public interface AddictionRepository {
    List<Addiction> findAll();
    void save(Addiction addiction);
}
