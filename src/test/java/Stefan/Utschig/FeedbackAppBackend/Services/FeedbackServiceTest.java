package Stefan.Utschig.FeedbackAppBackend.Services;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Models.CommentRepository;
import Stefan.Utschig.FeedbackAppBackend.Models.Feedback;
import Stefan.Utschig.FeedbackAppBackend.Models.FeedbackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {


    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private CommentRepository commentRepository;

    @Autowired
    @InjectMocks
    public FeedbackService feedbackService = new FeedbackService(feedbackRepository, commentRepository);

    private Feedback feedback;

    @Captor
    private ArgumentCaptor<Feedback> feedbackArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        Comment comment1 = Comment
                .builder()
                .content("A Test Comment 1")
                .build();
        Comment comment2 = Comment
                .builder()
                .content("A Test Comment 2")
                .build();
        this.feedback = Feedback
                .builder()
                .id(1L)
                .feedbackTitle("Add tags for solutions")
                .category("enhancement")
                .upvotes("122")
                .status("suggestion")
                .description("Easier to search for solutions based on a specific stack.")
                .comments(List.of(comment1, comment2))
                .build();
    }

    @Test
    @DisplayName("Should create Feedback once")
    public void createFeedbackTest() {
        Mockito.when(feedbackRepository.save(any())).thenReturn(this.feedback);
        feedbackService.createFeedback(this.feedback);
        verify(feedbackRepository, times(1)).save(feedbackArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should find Feedback by ID")
    public void getFeedbackByIdTest() {
        Mockito.when(feedbackRepository.findById(1L)).thenReturn(Optional.ofNullable(this.feedback));
        assertThat(feedbackService.getFeedbackById(String.valueOf(this.feedback.getId())))
                .isEqualTo(this.feedback);

    }

    @Test
    public void deleteTestById() {
        Mockito.when(feedbackService.deleteFeedbackById(String.valueOf(this.feedback.getId())))
                .thenReturn(Optional.ofNullable(feedback));
        feedbackService.deleteFeedbackById(String.valueOf(1L));
        verify(feedbackRepository, times(1)).deleteById(1L);


    }

    @After
    public void tearDown() throws Exception {
        this.feedback = null;
        this.feedbackRepository.deleteAll();
    }
}