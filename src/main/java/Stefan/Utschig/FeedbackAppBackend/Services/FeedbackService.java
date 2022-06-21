package Stefan.Utschig.FeedbackAppBackend.Services;

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

    public List<Feedback> getFeedbacks() {
        return this.feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(String id) {
        Optional<Feedback> optionalFeedback = this.feedbackRepository.findById(Long.valueOf(id));
        Feedback feedback = optionalFeedback.orElseThrow();
        return feedback;
    }

    public void createFeedback(Feedback feedback) {
        for (var comment : feedback.getComments()) {
            comment.setFeedback(feedback);
        }
        this.feedbackRepository.save(feedback);
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
        if (feedback.getComments().size() != updatedFeedback.getComments().size()) {
            updatedFeedback.getComments().clear();
            for (var comment : feedback.getComments()) {
                comment.setFeedback(updatedFeedback);
                updatedFeedback.getComments().add(comment);
            }
        }
    }

    public boolean deleteFeedbackById(String id) {
        if (this.feedbackRepository.findById(Long.valueOf(id)).isPresent()) {
            this.feedbackRepository.deleteById(Long.valueOf(id));
            return true;
        }
        return false;

    }
}
