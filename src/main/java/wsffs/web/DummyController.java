package wsffs.web;

import lombok.RequiredArgsConstructor;
import wsffs.springframework.beans.annotation.Component;
import wsffs.springframework.boot.web.servlet.anno.arguments.PathVariable;
import wsffs.springframework.boot.web.servlet.anno.mappings.GetMapping;
import wsffs.springframework.boot.web.servlet.anno.mappings.PostMapping;
import wsffs.springframework.boot.web.servlet.anno.present.RestController;

@Component
@RestController
@RequiredArgsConstructor
public class DummyController {

  private final DummyService dummyService;

  @GetMapping("/api/dummies")
  public String getDummies() {
    return dummyService.get().toString();
  }

  @PostMapping("/api/dummies")
  public String postDummies() {
    return "post /api/dummies";
  }

  @GetMapping("/api/dummies/{dummyId}")
  public String getPathVariable(@PathVariable("dummyId") Long dummyId) {
    return "GET /api/dummies/{dummyId} | " + dummyId;
  }
}
