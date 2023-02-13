package fr.simplex_software.rss.metrics.spring.mappers;

import fr.simplex_software.rss.metrics.spring.data.*;
import fr.simplex_software.rss.metrics.spring.model.*;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PressReleaseMapper
{
  @Mappings({@Mapping(source = "pressReleaseName", target = "name")})
  PressRelease toPressRelease(PressReleaseEntity PressReleaseEntity);
  @InheritInverseConfiguration
  PressReleaseEntity fromPressRelease(PressRelease PressRelease);
}
