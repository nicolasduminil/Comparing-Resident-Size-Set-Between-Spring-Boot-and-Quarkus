package fr.simplex_software.rss.metrics.quarkus.repositories;

import fr.simplex_software.rss.metrics.quarkus.data.*;
import io.quarkus.hibernate.orm.panache.*;

import javax.enterprise.context.*;
import java.util.*;

@ApplicationScoped
public class PressReleaseRepository implements PanacheRepository<PressReleaseEntity>
{
  public Optional<PressReleaseEntity> findByName (String name)
  {
    return find ("pressReleaseName", name).firstResultOptional();
  }
}
