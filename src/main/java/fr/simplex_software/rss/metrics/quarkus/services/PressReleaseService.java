package fr.simplex_software.rss.metrics.quarkus.services;

import fr.simplex_software.rss.metrics.quarkus.model.*;

import java.util.*;

public interface PressReleaseService
{
  Optional<PressRelease> getPressReleaseById (Long pressReleaseId);
  List<PressRelease> getPressReleases();
  boolean deletePressReleaseById(Long pressReleaseId);
  PressRelease createPressRelease (PressRelease pressRelease);
  PressRelease updatePressRelease(PressRelease pressRelease);
}
