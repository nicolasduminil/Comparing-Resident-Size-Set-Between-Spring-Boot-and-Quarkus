package fr.simplex_software.rss.metrics.quarkus.model;

import javax.validation.constraints.*;
import java.io.*;

public class PressRelease implements Serializable
{
  @Size(min=1, max=80)
  private String name;
  @Size(min=1, max=400)
  private String author;
  @Size(min=1, max=400)
  private String publisher;

  public PressRelease()
  {
  }

  public PressRelease(String name, String author, String publisher)
  {
    this.name = name;
    this.author = author;
    this.publisher = publisher;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
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
