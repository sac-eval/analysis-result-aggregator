package masters.aggregatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "custom_message")
@Getter
@Setter
@NoArgsConstructor
public class CustomMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rule_id", nullable = false, foreignKey = @ForeignKey(name = "custom_message_rule_violation_foreign_key"))
    private RuleViolation ruleViolation;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomMessageType messageType;

}
