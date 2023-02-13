package fr.simplex_software.rss.metrics.spring.model;

import java.io.*;

public class PressRelease implements Serializable
{
  private int pressReleaseId;
  private String name;
  private String author;
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

  public int getPressReleaseId()
  {
    return pressReleaseId;
  }

  public void setPressReleaseId(int pressReleaseId)
  {
    this.pressReleaseId = pressReleaseId;
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
