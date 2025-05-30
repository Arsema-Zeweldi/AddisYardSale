package org.web2.addisyardsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web2.addisyardsale.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
