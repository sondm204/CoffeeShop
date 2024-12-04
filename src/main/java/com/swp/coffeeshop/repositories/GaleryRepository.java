package com.swp.coffeeshop.repositories;

import com.swp.coffeeshop.models.ImageGalery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GaleryRepository extends JpaRepository<ImageGalery, Integer> {
}
