package edu.iastate.scoreboard;

public class Score {
	private long id;
	private String username;
	private String score;
	private String time;
	private String mode;
	
	public Score() {
		super();
	}

	public Score(long id,  String username, String score, String time, String mode,
			String details) {

		this.id = id;
		this.username = username;
		this.score = score;
		this.time = time;
		this.mode = mode;
		this.details = details;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	private String details;
	

}
