package masters.aggregatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
// INFO: This will one day, if there is need to have an admin dashboard, need to be refactored. As it is now, the way to use it is to simply connect to the DB and update the value.
@Where(clause = "disabled = false")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String url;

    @ManyToMany
    @JoinTable(name = "tool_language",
        joinColumns = @JoinColumn(name = "tool_id", foreignKey = @ForeignKey(name = "tool_language_tool_foreign_key")),
        inverseJoinColumns = @JoinColumn(name = "language_id", foreignKey = @ForeignKey(name = "tool_language_language_foreign_key"))
    )
    private Set<Language> languages;

    @Column(nullable = false)
    private boolean disabled;

    public Tool(String name) {
        this.name = name;
    }
}
