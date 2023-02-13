package fr.simplex_software.rss.metrics.spring.services;

import fr.simplex_software.rss.metrics.spring.model.*;

import java.util.*;

public interface PressReleaseService
{
  Optional<PressRelease> getPressReleaseById (int pressReleaseId);
  List<PressRelease> getPressReleases();
  boolean deletePressReleaseById(int pressReleaseId);
  Optional<PressRelease> saveOrUpdatePressRelease(PressRelease pressRelease);
}
