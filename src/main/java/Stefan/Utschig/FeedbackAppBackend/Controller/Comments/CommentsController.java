package Stefan.Utschig.FeedbackAppBackend.Controller.Comments;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class CommentsController {

    private CommentService commentService;

    @PutMapping("{id}")
    public void updateComments(@PathVariable String id, @RequestBody Comment comment) {
        this.commentService.updateComments(id, comment);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComments(@PathVariable String id) {
        this.commentService.deleteComment(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
