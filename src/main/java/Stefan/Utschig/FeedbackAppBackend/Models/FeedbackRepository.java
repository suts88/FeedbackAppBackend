package Stefan.Utschig.FeedbackAppBackend.Models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback,Long> {

    @Override
    List<Feedback> findAll();

    @Modifying
    @Query(value = "update Feedback f " +
            "set " +
            "f.feedbackTitle = :#{#feedback.feedbackTitle}, " +
            "f.category = :#{#feedback.category}, " +
            "f.upvotes = :#{#feedback.upvotes}, " +
            "f.status = :#{#feedback.status}, " +
            "f.description = :#{#feedback.description}, " +
            "f.comments = :#{#feedback.comments} "+
            "where f.id = :id"
    )
    public void updateFeedback(Long id,
            Feedback feedback
    );

}
