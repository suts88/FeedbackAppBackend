package Stefan.Utschig.FeedbackAppBackend.Services;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Models.CommentRepository;
import Stefan.Utschig.FeedbackAppBackend.Models.Feedback;
import Stefan.Utschig.FeedbackAppBackend.Models.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class FeedbackService {

    private FeedbackRepository feedbackRepository;
    private CommentRepository commentRepository;

    public List<Feedback> getFeedbacks() {
        return this.feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(String id) {
        Optional<Feedback> optionalFeedback = this.feedbackRepository.findById(Long.valueOf(id));
        Feedback feedback = optionalFeedback.orElseThrow();
        return feedback;
    }

    public Feedback createFeedback(Feedback feedback) {
        if(feedback.getComments() != null) {
            for (var comment : feedback.getComments()) {
                comment.setFeedback(feedback);
            }
        }
        this.feedbackRepository.save(feedback);
        return feedback;
    }

    public void updateComments(String id, Comment comment) {
        Feedback updatedFeedback = this.getFeedbackById(id);
        comment.setFeedback(updatedFeedback);
        this.commentRepository.save(comment);
    }

    public void updateUpvotes(String id, String upvotes){
        Feedback updatedFeedback = this.getFeedbackById(id);
        updatedFeedback.setUpvotes(upvotes);
        this.feedbackRepository.save(updatedFeedback);
    }

    public void updateFeedback(String id, Feedback feedback) {

        Feedback updatedFeedback = this.getFeedbackById(id);

        if (feedback.getFeedbackTitle() != null) {
            updatedFeedback.setFeedbackTitle(feedback.getFeedbackTitle());
        }
        if (feedback.getCategory() != null) {
            updatedFeedback.setCategory(feedback.getCategory());
        }
        if (feedback.getUpvotes() != null) {
            updatedFeedback.setUpvotes(feedback.getUpvotes());
        }
        if (feedback.getStatus() != null) {
            updatedFeedback.setStatus(feedback.getStatus());
        }
        if (feedback.getDescription() != null) {
            updatedFeedback.setDescription(feedback.getDescription());
        }
        if (feedback.getComments() != null) {
            updatedFeedback.getComments().clear();
            for (var comment : feedback.getComments()) {
                comment.setFeedback(updatedFeedback);
                updatedFeedback.getComments().add(comment);
            }

        }


    }

    public Optional<Feedback> deleteFeedbackById(String id) {
        Optional<Feedback> feedback = feedbackRepository.findById(Long.valueOf(id));
        if (feedback.isPresent()) {
            this.feedbackRepository.deleteById(Long.valueOf(id));
            return feedback;
        }
        return Optional.empty();

    }
}
