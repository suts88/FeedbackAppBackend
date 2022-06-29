package Stefan.Utschig.FeedbackAppBackend.Controller.Comments;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
@CrossOrigin(origins = {"http://localhost:8080/comments", "http://localhost:4200"})
@AllArgsConstructor
public class CommentsController {

    private CommentService commentService;

    @PutMapping("{id}")
    public void updateComments(@PathVariable String id, @RequestBody Comment comment) {
        this.commentService.updateComments(id, comment);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public void deleteComments(@PathVariable String id) {
        this.commentService.deleteComment(id);
    }


}
