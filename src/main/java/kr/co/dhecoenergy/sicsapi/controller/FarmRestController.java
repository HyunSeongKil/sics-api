package kr.co.dhecoenergy.sicsapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dhecoenergy.sicsapi.domain.FarmDto;
import kr.co.dhecoenergy.sicsapi.misc.SicsRestController;
import kr.co.dhecoenergy.sicsapi.service.FarmService;

@RestController
@RequestMapping("/farms")
public class FarmRestController extends SicsRestController {

  private FarmService service;

  public FarmRestController(FarmService service) {
    this.service = service;
  }

  @DeleteMapping("/{farmId}")
  public void delete(HttpServletRequest request, @PathVariable long farmId) {
    long farmerId = jwtService.getUserId(request);

    service.deleteByFarmIdAndFarmerId(farmId, farmerId);
  }

  @GetMapping("/{farmId}")
  public ResponseEntity<FarmDto> get(HttpServletRequest request, @PathVariable long farmId) {
    long farmerId = jwtService.getUserId(request);

    return ResponseEntity.ok(service.getByFarmIdAndFarmerId(farmId, farmerId));
  }

  @GetMapping("")
  public ResponseEntity<List<FarmDto>> gets(HttpServletRequest request) {
    long farmerId = jwtService.getUserId(request);

    return ResponseEntity.ok(service.getsByFarmerId(farmerId));
  }

  @PostMapping()
  public ResponseEntity<Long> regist(HttpServletRequest request, @RequestParam FarmDto dto) {
    long farmerId = jwtService.getUserId(request);
    dto.setFarmId(farmerId);

    return ResponseEntity.ok(service.regist(dto));
  }

  @PutMapping()
  public void update(HttpServletRequest request, @RequestParam FarmDto dto) {
    long farmerId = jwtService.getUserId(request);
    dto.setFarmId(farmerId);

    service.update(dto);
  }
}
