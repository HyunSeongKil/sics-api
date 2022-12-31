package kr.co.dhecoenergy.sicsapi.misc;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.dhecoenergy.sicsapi.domain.AtchmnflDto;
import kr.co.dhecoenergy.sicsapi.service.AtchmnflGroupService;
import kr.co.dhecoenergy.sicsapi.service.AtchmnflService;
import kr.co.dhecoenergy.sicsapi.service.JwtService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SicsRestController {

  @Autowired
  private AtchmnflGroupService atchmnflGroupService;
  @Autowired
  private AtchmnflService atchmnflService;
  @Autowired
  protected JwtService jwtService;

  @PostMapping("/upload")
  public ResponseEntity<Long> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
    long atchmnflGroupId = atchmnflGroupService.regist();
    atchmnflService.processRegist(atchmnflGroupId, files);

    return ResponseEntity.ok(atchmnflGroupId);
  }

  @GetMapping("/download/{atchmnflId}")
  public ResponseEntity<Resource> downloadFile(@PathVariable long atchmnflId) throws Exception {
    AtchmnflDto dto = atchmnflService.getById(atchmnflId);
    File file = atchmnflService.getFileById(atchmnflId);

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + dto.getOriginalFilename() + "\"")
        .header(HttpHeaders.CONTENT_TYPE, dto.getContentType())
        .header(HttpHeaders.CONTENT_LENGTH, "" + file.length())
        .body(resource);
  }

}
