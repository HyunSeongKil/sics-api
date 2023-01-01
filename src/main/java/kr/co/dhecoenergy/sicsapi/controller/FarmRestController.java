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
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

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
  public ResponseEntity<CmmnResultMap> get(HttpServletRequest request, @PathVariable long farmId) {
    long farmerId = jwtService.getUserId(request);

    FarmDto dto = service.getByFarmIdAndFarmerId(farmId, farmerId);

    return ResponseEntity.ok(CmmnResultMap.withData(dto));
  }

  @GetMapping("")
  public ResponseEntity<CmmnResultMap> gets(HttpServletRequest request) {
    long farmerId = jwtService.getUserId(request);

    List<FarmDto> dtos = service.getsByFarmerId(farmerId);

    return ResponseEntity.ok(CmmnResultMap.withData(dtos));
  }

  @PostMapping()
  public ResponseEntity<CmmnResultMap> regist(HttpServletRequest request, @RequestParam FarmDto dto) {
    long farmerId = jwtService.getUserId(request);
    dto.setFarmId(farmerId);

    long farmId = service.regist(dto);
    return ResponseEntity.ok(CmmnResultMap.withData(farmId));
  }

  @PutMapping()
  public void update(HttpServletRequest request, @RequestParam FarmDto dto) {
    long farmerId = jwtService.getUserId(request);
    dto.setFarmId(farmerId);

    service.update(dto);
  }
}
