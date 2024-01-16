package com.andrein.teamtackle.repository;

import com.andrein.teamtackle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
