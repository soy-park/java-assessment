package example.com.assessment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private long userId;

  private String title;
  private boolean done = false;

  public long getId() { return this.id; }
  public void setId(long id) { this.id = id; }

  public long userId() { return this.userId; }
  public void setUserId(long userId) { this.userId = userId; }

  public String getTitle() { return this.title; }
  public void setTitle(String title) { this.title = title; }

  public boolean getDone() { return this.done; }
  public void setDone(boolean done) { this.done = done; }
}
