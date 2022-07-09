package Stefan.Utschig.FeedbackAppBackend.Controller.Feedback;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Models.Feedback;
import Stefan.Utschig.FeedbackAppBackend.Services.FeedbackService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedbacks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class FeedbackController {

    private FeedbackService feedbackService;

    @GetMapping("all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return new ResponseEntity<>(this.feedbackService.getFeedbacks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable String id) {
        return new ResponseEntity<>(this.feedbackService.getFeedbackById(id), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Feedback> createFeedback(@RequestBody @NotNull Feedback feedback) {
        this.feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }


    @PutMapping("{id}/upvotes")
    public ResponseEntity<String> updateFeedbackUpvotes(@PathVariable String id, @RequestBody String upvotes){
        this.feedbackService.updateUpvotes(id, upvotes);
        return new ResponseEntity<>(upvotes, HttpStatus.OK);

    }



    @PutMapping("{id}")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody @NotNull Feedback feedback, @PathVariable String id) {
        this.feedbackService.updateFeedback(id, feedback);
        return new ResponseEntity<>(feedback,HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable String id) {
        if (this.feedbackService.deleteFeedbackById(id).isPresent()) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
    }
}
