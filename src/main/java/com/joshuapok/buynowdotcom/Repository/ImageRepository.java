package com.joshuapok.buynowdotcom.Repository;

import com.joshuapok.buynowdotcom.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
