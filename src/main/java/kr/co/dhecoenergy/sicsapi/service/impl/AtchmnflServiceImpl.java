package kr.co.dhecoenergy.sicsapi.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.dhecoenergy.sicsapi.domain.AtchmnflDto;
import kr.co.dhecoenergy.sicsapi.entity.Atchmnfl;
import kr.co.dhecoenergy.sicsapi.misc.SicsUtils;
import kr.co.dhecoenergy.sicsapi.repository.AtchmnflRepository;
import kr.co.dhecoenergy.sicsapi.service.AtchmnflGroupService;
import kr.co.dhecoenergy.sicsapi.service.AtchmnflService;
import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;
import kr.vaiv.sdt.cmmn.misc.CmmnUtils;

@Service
public class AtchmnflServiceImpl implements AtchmnflService {
  @Value("${app.file.upload.path}")
  private String fileUploadPath;

  private AtchmnflRepository repo;
  private AtchmnflGroupService atchmnflGroupService;

  public AtchmnflServiceImpl(AtchmnflRepository repo, AtchmnflGroupService atchmnflGroupService) {
    super();
    this.repo = repo;
    this.atchmnflGroupService = atchmnflGroupService;
  }

  @Override
  @Transactional
  public long processRegist(MultipartFile file) throws Exception {
    //
    Long atchmnflGroupId = this.atchmnflGroupService.regist();

    return processRegist(atchmnflGroupId, file);

  }

  @Override
  @Transactional
  public long regist(AtchmnflDto dto) throws Exception {
    return repo.save(CmmnBeanUtils.dtoToEntity(dto, Atchmnfl.class)).getAtchmnflId();
  }

  @Override
  public List<AtchmnflDto> getsByAtchmnflGroupId(long atchmnflGroupId) {
    //
    List<Atchmnfl> entities = repo.findAllByAtchmnflGroupId(atchmnflGroupId);

    //
    List<AtchmnflDto> dtos = entities.stream().map(entity -> {
      try {
        return CmmnBeanUtils.entityToDto(entity, AtchmnflDto.class);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList());

    //
    return dtos;
  }

  @Override
  @Transactional
  public void deleteById(long atchmnflId) throws Exception {
    //
    AtchmnflDto dto = getById(atchmnflId);
    if (null == dto) {
      return;
    }

    //
    Files.delete(Paths.get(fileUploadPath, dto.getSavedSubPath(), dto.getSavedFilename()));

    //
    repo.deleteById(atchmnflId);

    //
    if (0 == repo.countByAtchmnflGroupId(dto.getAtchmnflGroupId())) {
      atchmnflGroupService.deleteById(dto.getAtchmnflGroupId());
    }
  }

  @Override
  public AtchmnflDto getById(long atchmnflId) {
    //
    Optional<Atchmnfl> opt = repo.findById(atchmnflId);
    if (opt.isEmpty()) {
      return null;
    }

    try {
      return CmmnBeanUtils.entityToDto(opt.get(), AtchmnflDto.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public File getFileById(long atchmnflId) throws Exception {
    AtchmnflDto dto = getById(atchmnflId);

    return Paths.get(fileUploadPath, dto.getSavedSubPath(), dto.getSavedFilename()).toFile();
  }

  @Override
  public List<Long> processRegist(List<MultipartFile> files) {
    long atchmnflGroupId = atchmnflGroupService.regist();

    //
    return processRegist(atchmnflGroupId, files);
  }

  @Override
  @Transactional
  public long processRegist(long atchmnflGroupId, MultipartFile file) throws IOException {
    // dto 생성
    AtchmnflDto dto = AtchmnflDto.builder()
        .atchmnflGroupId(atchmnflGroupId)
        .originalFilename(file.getOriginalFilename())
        .fileSize(file.getSize())
        .contentType(file.getContentType())
        .savedFilename(String.join(".", CmmnUtils.shortUuid(), CmmnUtils.getExtension(file.getName())))
        .savedSubPath(Paths.get(LocalDate.now().getYear() + "", LocalDate.now().getMonth().getValue() + "").toString())
        .build();

    // 파일 저장
    Files.createDirectories(Paths.get(fileUploadPath, dto.getSavedSubPath(), dto.getSavedFilename()));
    file.transferTo(Paths.get(fileUploadPath, dto.getSavedSubPath(), dto.getSavedFilename()));

    // 데이터 저장
    return repo.save(SicsUtils.dtoToEntity(dto, Atchmnfl.class))
        .getAtchmnflId();
  }

  @Override
  public List<Long> processRegist(long atchmnflGroupId, List<MultipartFile> files) {
    List<Long> atchmnflIds = files.stream().map(f -> {
      try {
        return processRegist(atchmnflGroupId, f);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList());

    //
    return atchmnflIds;
  }

}
