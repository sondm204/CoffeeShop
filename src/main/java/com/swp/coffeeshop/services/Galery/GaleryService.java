package com.swp.coffeeshop.services.Galery;

import com.swp.coffeeshop.models.ImageGalery;
import com.swp.coffeeshop.repositories.GaleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaleryService implements IGaleryService {
    GaleryRepository galeryRepository;

    public GaleryService(GaleryRepository galeryRepository) {
        this.galeryRepository = galeryRepository;
    }

    @Override
    public List<ImageGalery> getAllGaleries() {
        return galeryRepository.findAll();
    }
}
