package masters.aggregatorservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
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

    public Tool(String name) {
        this.name = name;
    }
}
