package masters.aggregatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "rule_violation")
@Getter
@Setter
public class RuleViolation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleSarifId;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "rule_violation_language_foreign_key"))
    private Language language;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "rule_violation_tool_foreign_key"))
    private Tool tool;

    @OneToMany(mappedBy = "ruleViolation")
    private Set<CustomMessage> customMessages;

}
