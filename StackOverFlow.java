/* 
* Requirements:
*	1. post new questions
*	2. add answer to new question
*	3. add comments to answer/question
*	4. upvote answer/question/comments
*	5. flag  answer/question/comments
*	6. Add tags to question
*	7. close a question
*	8. frequently used to tags
*/

/* 
* Actors:
*	1. Guest : register, search, view
*	2. Members : post/modify question/answer/comment
*	3. Admin : account management
*	4. Moderator : close/delete/undelete any question
*	5. System : notifications, badges
*/


// Constants
public enum QuestionStatus{
  OPEN,
  CLOSED,
  ON_HOLD,
  DELETED
}

public enum QuestionClosingRemark{
  DUPLICATE,
  OFF_TOPIC,
  TOO_BROAD,
  NOT_CONSTRUCTIVE,
  NOT_A_REAL_QUESTION,
  PRIMARILY_OPINION_BASED
}

public enum AccountStatus{
  ACTIVE,
  CLOSED,
  CANCELED,
  BLACKLISTED,
  BLOCKED
}

// Account
public class Account {
  private String id;
  private String password;
  private AccountStatus status;
  private String name;
  private Address address;
  private String email;
  private String phone;
  private int reputation;

  public boolean resetPassword();
}
public class Guest {
	int guestId;
	public void register();
}

public class Member {
  private Account account;
  private List<Badge> badges;

  public int getReputation();
  public String getEmail();
  public boolean createQuestion(Question question);
  public boolean createTag(Tag tag);
}

public class Admin extends Member {
  public boolean blockMember(Member member);
  public boolean unblockMember(Member member);
}

public class Moderator extends Member {
  public boolean closeQuestion(Question question);
  public boolean undeleteQuestion(Question question);
}

// Badge, Tag, and Notification:
public class Badge {
  private String name;
  private String description;
}

public class Tag {
  private String name;
  private String description;
  private long dailyAskedFrequency;
  private long weeklyAskedFrequency;
}

public class Notification {
  private int notificationId;
  private Date createdOn;
  private String content;

  public boolean sendNotification();
}

// Questions
public interface Search {
  public static List<Question> search(String query);
}

public class Entity {

	int entityId;
	Member creator;
	Map<VoteType, Integer> votes;
	Date createdDate;
	List<Comment> comments;

	public boolean flagEntity(Member member);
	public boolean voteEntity(VoteType voteType);
	public boolean addComment(Comment comment);

}

public class Question extends Entity implements Search {
	private String title;
	private String description;
	private QuestionStatus status;
	private QuestionClosingRemark closingRemark;

	private List<Answer> answers;
	private List<Tag> tags;

	public boolean close();
	public boolean undelete();
	public boolean addComment(Comment comment);
	public boolean addTag(Tag tag);

	public static List<Question> search(String query) {
		// return all questions containing the string query in their title or description.
	}
}

public class Comment extends Entity  {
	private String text;
}

public class Answer extends Entity  {
	private String answerText;
	private boolean accepted;
	public boolean addComment(Comment comment);
}





























