package org.web2.addisyardsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web2.addisyardsale.model.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
