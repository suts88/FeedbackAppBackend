package Stefan.Utschig.FeedbackAppBackend.Services;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Models.CommentRepository;
import Stefan.Utschig.FeedbackAppBackend.Models.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private FeedbackService feedbackService;

    public void updateComments(String id, Comment comment) {
        Feedback updatedFeedback = this.feedbackService.getFeedbackById(id);
        comment.setFeedback(updatedFeedback);
        this.commentRepository.save(comment);
    }

    public void deleteComment(String id){
        this.commentRepository.deleteById(Long.valueOf(id));
    }

}
