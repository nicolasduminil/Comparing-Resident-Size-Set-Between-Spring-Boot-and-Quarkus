package fr.simplex_software.rss.metrics.quarkus.services.impl;

import fr.simplex_software.rss.metrics.quarkus.data.*;
import fr.simplex_software.rss.metrics.quarkus.mappers.*;
import fr.simplex_software.rss.metrics.quarkus.model.*;
import fr.simplex_software.rss.metrics.quarkus.repositories.*;
import fr.simplex_software.rss.metrics.quarkus.services.*;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.transaction.*;
import java.util.*;
import java.util.stream.*;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PressReleaseServiceImpl implements PressReleaseService
{
  @Inject
  PressReleaseRepository pressReleaseRepository;
  @Inject
  PressReleaseMapper pressReleaseMapper;

  @Override
  public Optional<PressRelease> getPressReleaseById(Long pressReleaseId)
  {
    return Optional.of(pressReleaseMapper.toPressRelease(pressReleaseRepository.findById(pressReleaseId)));
  }

  @Override
  public List<PressRelease> getPressReleases()
  {
    return pressReleaseRepository.findAll().stream().map(pressReleaseMapper::toPressRelease).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public boolean deletePressReleaseById(Long pressReleaseId)
  {
    pressReleaseRepository.deleteById(pressReleaseId);
    return true;
  }

  @Override
  @Transactional
  public PressRelease createPressRelease(PressRelease pressRelease)
  {
    pressReleaseRepository.persist(pressReleaseMapper.fromPressRelease(pressRelease));
    return pressRelease;
  }

  @Override
  @Transactional
  public PressRelease updatePressRelease(PressRelease pressRelease)
  {
    Optional<PressReleaseEntity> pressReleaseEntity = pressReleaseRepository.findByName(pressRelease.getName());
    pressReleaseEntity.ifPresent(pre ->
    {
      pressReleaseRepository.update("pressReleaseName", pressRelease.getName());
      pressReleaseRepository.update("author", pressRelease.getAuthor());
      pressReleaseRepository.update("publisher", pressRelease.getPublisher());
    });
    pressReleaseRepository.flush();
    return pressRelease;
  }
}
