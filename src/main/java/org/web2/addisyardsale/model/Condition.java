package org.web2.addisyardsale.model;

import jakarta.persistence.*;

@Entity
@Table(name="conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ConditionId;
    private String ConditionName;

    public Condition(String aNew) {
        this.ConditionName = aNew;
    }

    public Condition() {

    }

    public Long getConditionId() {
        return ConditionId;
    }

    public void setConditionId(Long conditionId) {
        ConditionId = conditionId;
    }

    public String getConditionName() {
        return ConditionName;
    }

    public void setConditionName(String conditionName) {
        ConditionName = conditionName;
    }
}
