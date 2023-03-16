package masters.aggregatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "statistic_rule_violation_foreign_key"))
    private RuleViolation ruleViolation;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "statistic_tenant_foreign_key"))
    private Tenant tenant;

    @Column(nullable = false)
    private Long occurrence;
}
