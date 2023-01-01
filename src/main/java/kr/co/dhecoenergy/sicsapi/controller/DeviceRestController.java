package kr.co.dhecoenergy.sicsapi.controller;

import java.util.List;
import java.util.Map;

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

import kr.co.dhecoenergy.sicsapi.domain.DeviceDto;
import kr.co.dhecoenergy.sicsapi.domain.FarmDto;
import kr.co.dhecoenergy.sicsapi.misc.SicsRestController;
import kr.co.dhecoenergy.sicsapi.service.DeviceService;
import kr.co.dhecoenergy.sicsapi.service.FarmService;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

@RestController
@RequestMapping("/devices")
public class DeviceRestController extends SicsRestController {
  private DeviceService service;
  private FarmService farmService;

  public DeviceRestController(DeviceService service, FarmService farmService) {
    super();
    this.service = service;
    this.farmService = farmService;
  }

  @GetMapping("/farms/{farmId}")
  public ResponseEntity<CmmnResultMap> gets(HttpServletRequest request, @PathVariable long farmId) {

    FarmDto farmDto = farmService.getById(farmId);
    if (null == farmDto) {
      // 농장정보 없음
      return ResponseEntity.ok(CmmnResultMap.of(List.of(), "E_FARM_NOT_FOUND"));
    }

    long farmerId = jwtService.getUserId(request);
    if (farmDto.getFarmerId() != farmerId) {
      // 본인 소유 농장 아님
      return ResponseEntity.ok(CmmnResultMap.of(List.of(), "E_NOT_MY_FARM"));
    }

    //
    List<DeviceDto> dtos = service.getsByFarmId(farmId);
    return ResponseEntity.ok(CmmnResultMap.withData(dtos));
  }

  @GetMapping("/{deviceId}")
  public ResponseEntity<CmmnResultMap> get(HttpServletRequest request, @PathVariable long deviceId) {
    DeviceDto dto = service.getById(deviceId);

    FarmDto farmDto = farmService.getById(dto.getFarmId());
    if (null == farmDto) {
      // 농장정보 없음
      return ResponseEntity.ok(CmmnResultMap.of(Map.of(), "E_FARM_NOT_FOUND"));
    }

    long farmerId = jwtService.getUserId(request);
    if (farmDto.getFarmerId() != farmerId) {
      // 본인 소유의 농장 아님
      return ResponseEntity.ok(CmmnResultMap.of(List.of(), "E_NOT_MY_FARM"));
    }

    return ResponseEntity.ok(CmmnResultMap.withData(dto));

  }

  @PostMapping
  public ResponseEntity<CmmnResultMap> regist(HttpServletRequest request, @RequestParam DeviceDto dto) {
    FarmDto farmDto = farmService.getById(dto.getFarmId());
    if (null == farmDto) {
      // 농장정보 없음
      return ResponseEntity.ok(CmmnResultMap.of(-1L, "E_FARM_NOT_FOUND"));
    }

    long farmerId = jwtService.getUserId(request);
    if (farmDto.getFarmerId() != farmerId) {
      // 본인 소유의 농장이 아님
      ResponseEntity.ok(CmmnResultMap.of(-1L, "E_NOT_MY_FARM"));
    }

    return ResponseEntity.ok(CmmnResultMap.withData(service.regist(dto)));
  }

  @PutMapping
  public void update(HttpServletRequest request, @RequestParam DeviceDto dto) {
    if (null == service.getById(dto.getDeviceId())) {
      // 장치정보 없음
      return;
    }

    FarmDto farmDto = farmService.getById(dto.getFarmId());
    if (null == farmDto) {
      // 농장정보 없음
      return;
    }

    long farmerId = jwtService.getUserId(request);
    if (farmDto.getFarmerId() != farmerId) {
      // 본인 소유의 농장이 아님
      return;
    }

    service.update(dto);
  }

  @DeleteMapping("/{deviceId}")
  public void delete(HttpServletRequest request, @PathVariable long deviceId) {
    DeviceDto dto = service.getById(deviceId);
    if (null == dto) {
      // 장치정보 없음
      return;
    }

    FarmDto farmDto = farmService.getById(dto.getFarmId());
    if (null == farmDto) {
      // 농장정보 없음
      return;
    }

    long farmerId = jwtService.getUserId(request);
    if (farmDto.getFarmerId() != farmerId) {
      // 본인 소유의 농장이 아님
      return;
    }

    service.deleteById(deviceId);
  }

}
