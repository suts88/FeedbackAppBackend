package Stefan.Utschig.FeedbackAppBackend;

import Stefan.Utschig.FeedbackAppBackend.Models.Comment;
import Stefan.Utschig.FeedbackAppBackend.Models.CommentRepository;
import Stefan.Utschig.FeedbackAppBackend.Models.Feedback;
import Stefan.Utschig.FeedbackAppBackend.Models.FeedbackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FeedbackAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackAppBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			FeedbackRepository feedbackRepository,
			CommentRepository commentRepository){
		return args -> {

			Comment comment1 = Comment
					.builder()
					.content("Awesome idea! Trying to find framework-specific projects within the hubs can be tedious")
					.build();

			Comment comment2 = Comment
					.builder()
					.content("Please use fun, color-coded labels to easily identify them at a glance")
					.build();

			Feedback feedback1 = Feedback
					.builder()
					.feedbackTitle("Add tags for solutions")
					.category("enhancement")
					.upvotes("122")
					.status("suggestion")
					.description("Easier to search for solutions based on a specific stack.")
					.comments(List.of(comment1,comment2))
					.build();

			comment1.setFeedback(feedback1);
			comment2.setFeedback(feedback1);
			feedbackRepository.save(feedback1);

		};
	}
}
