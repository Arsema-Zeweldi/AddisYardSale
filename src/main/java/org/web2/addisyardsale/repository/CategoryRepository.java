package org.web2.addisyardsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web2.addisyardsale.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
