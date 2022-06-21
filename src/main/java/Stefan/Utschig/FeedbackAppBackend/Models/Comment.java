package Stefan.Utschig.FeedbackAppBackend.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment  {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne()
    @JoinColumn(name = "feedback_id")
    @JsonIgnore
    private Feedback feedback;

    @Override
    public String toString() {
        return "{" +
                "content='" + content + '\'' +
                ", feedback=" + feedback.getId() +
                '}';
    }
}
