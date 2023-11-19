package fr.simplex_software.rss.metrics.spring.controllers;

import fr.simplex_software.rss.metrics.spring.aspect.RestLog;
import fr.simplex_software.rss.metrics.spring.model.*;
import fr.simplex_software.rss.metrics.spring.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rss")
public class PressReleaseController
{
  private final PressReleaseService pressReleaseService;

  @Autowired
  public PressReleaseController(PressReleaseService pressReleaseService)
  {
    this.pressReleaseService = pressReleaseService;
  }

  @RestLog
  @GetMapping("/all")
  public Collection<PressRelease> getAllPressReleases()
  {
    return pressReleaseService.getPressReleases();
  }

  @RestLog
  @GetMapping("/pressRelease/{id}")
  public ResponseEntity<PressRelease> getPressRelease(@PathVariable Integer id)
  {
    return pressReleaseService.getPressReleaseById(id).map(ResponseEntity::ok).orElseGet(() ->
      new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RestLog
  @PostMapping("/add")
  public ResponseEntity<PressRelease> addPressRelease(@RequestBody PressRelease pressRelease)
  {
    return pressReleaseService.saveOrUpdatePressRelease(pressRelease).map(ResponseEntity::ok).orElseGet(() ->
      new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
  }

  @RestLog
  @PutMapping("/update")
  public ResponseEntity<PressRelease> editPressRelease(@RequestBody PressRelease pressRelease)
  {
    return pressReleaseService.saveOrUpdatePressRelease(pressRelease).map(ResponseEntity::ok).orElseGet(() ->
      new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
  }

  @RestLog
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deletePressRelease(@PathVariable("id") int pressReleaseId)
  {
    return pressReleaseService.deletePressReleaseById(pressReleaseId) ? ResponseEntity.ok("PressRelease is removed") :
      new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
  }
}
