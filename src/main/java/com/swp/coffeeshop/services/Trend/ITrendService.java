package com.swp.coffeeshop.services.Trend;

import com.swp.coffeeshop.models.Trend;

import java.util.List;
import java.util.Optional;

public interface ITrendService {
    public List<Trend> getAll();

    public Trend getTrendActive();

    public Optional<Trend> getById(int id);
}
