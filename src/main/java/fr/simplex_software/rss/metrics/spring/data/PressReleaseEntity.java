package fr.simplex_software.rss.metrics.spring.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "PRESS_RELEASE")
public class PressReleaseEntity
{
  public PressReleaseEntity()
  {
  }

  public PressReleaseEntity(String pressReleaseName, String author, String publisher)
  {
    super();
    this.pressReleaseName = pressReleaseName;
    this.author = author;
    this.publisher = publisher;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int pressReleaseId;

  @NotEmpty
  @Column(name="PRESS_RELEASE_NAME", nullable = false, length = 80)
  private String pressReleaseName;

  @NotEmpty
  @Column(name="PRESS_RELEASE_AUTHOR", nullable = false, length = 400)
  private String author;

  @NotEmpty
  @Column(name="PRESS_RELEASE_PUBLISHER", nullable = false, length = 400)
  private String publisher;

  public int getPressReleaseId()
  {
    return pressReleaseId;
  }

  public void setPressReleaseId(int pressReleaseId)
  {
    this.pressReleaseId = pressReleaseId;
  }

  public String getPressReleaseName()
  {
    return pressReleaseName;
  }

  public void setPressReleaseName(String pressReleaseName)
  {
    this.pressReleaseName = pressReleaseName;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public String getPublisher()
  {
    return publisher;
  }

  public void setPublisher(String publisher)
  {
    this.publisher = publisher;
  }
}
