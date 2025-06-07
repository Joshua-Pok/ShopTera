package com.joshuapok.buynowdotcom.Repository;

import com.joshuapok.buynowdotcom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
