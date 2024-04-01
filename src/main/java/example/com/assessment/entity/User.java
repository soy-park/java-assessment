package example.com.assessment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = "username"),
})
public class User {

  public User() {

  }

  public User(String username, String password, String firstname, String lastname) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String password;
  private String firstname;
  private String lastname;

  public long getId() { return this.id; }

  public void setUsername(String username) { this.username = username; }
  public String getUsername() { return this.username; }

  public void setPassword(String password) { this.password = password; }
  public String getPassword() { return this.password; }

  public void setFirstname(String firstname) { this.firstname = firstname; }
  public String getFirstname() { return this.firstname; }

  public void setLastname(String lastname) { this.lastname = lastname; }
  public String getLastname() { return this.lastname; }
}
