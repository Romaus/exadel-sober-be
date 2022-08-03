package com.exadel.sober.repositories;

import com.exadel.sober.models.Addiction;
import java.util.List;

public interface AddictionRepository {

    List<Addiction> findAll();
    Addiction save(Addiction addiction);
    Addiction findById(Integer addiction_id);
}
