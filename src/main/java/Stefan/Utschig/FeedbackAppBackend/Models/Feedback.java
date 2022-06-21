package Stefan.Utschig.FeedbackAppBackend.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String feedbackTitle;

    private String category;

    private String upvotes;

    private String status;

    private String description;

    @OneToMany(
            mappedBy = "feedback",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            targetEntity = Comment.class
    )
    private List<Comment> comments;

}
