package fr.simplex_software.rss.metrics.quarkus.mappers;

import fr.simplex_software.rss.metrics.quarkus.data.*;
import fr.simplex_software.rss.metrics.quarkus.model.*;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface PressReleaseMapper
{
  @Mapping(source = "pressReleaseName", target = "name")
  PressRelease toPressRelease(PressReleaseEntity PressReleaseEntity);
  @Mapping(target = "pressReleaseId", ignore = true)
  @Mapping(source = "name", target = "pressReleaseName")
  PressReleaseEntity fromPressRelease(PressRelease PressRelease);
}
