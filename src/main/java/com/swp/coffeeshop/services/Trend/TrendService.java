package com.swp.coffeeshop.services.Trend;

import com.swp.coffeeshop.models.Trend;
import com.swp.coffeeshop.repositories.TrendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrendService implements ITrendService {
    TrendRepository trendRepository;

    public TrendService(TrendRepository trendRepository) {
        this.trendRepository = trendRepository;
    }

    @Override
    public List<Trend> getAll() {
        return trendRepository.findAll();
    }

    @Override
    public Trend getTrendActive() {
        return getAll().stream().filter(t -> t.getActive() == 1).findFirst().get();
    }

    @Override
    public Optional<Trend> getById(int id) {
        return trendRepository.findById(id);
    }

}
