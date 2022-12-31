package kr.co.dhecoenergy.sicsapi.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.dhecoenergy.sicsapi.domain.AtchmnflDto;

public interface AtchmnflService {

  long processRegist(long atchmnflGroupId, MultipartFile file) throws IOException;

  List<Long> processRegist(long atchmnflGroupId, List<MultipartFile> files);

  List<Long> processRegist(List<MultipartFile> files);

  /**
   * 파일 정보 등록
   * 1. 첨부파일 그룹 정보 등록
   * 2. 첨부파일 정보 등록
   * 3. 파일 저장
   * 
   * @param file
   * @return
   * @throws Exception
   */
  long processRegist(MultipartFile file) throws Exception;

  long regist(AtchmnflDto dto) throws Exception;

  /**
   * 삭제
   * 1. 데이터 삭제
   * 2. 파일 삭제
   * ※ 모든 데이터 삭제되면 첨부파일그룹 데이터도 삭제함
   * 
   * @param atchmnflId
   * @throws Exception
   */
  void deleteById(long atchmnflId) throws Exception;

  AtchmnflDto getById(long atchmnflId);

  List<AtchmnflDto> getsByAtchmnflGroupId(long atchmnflGroupId);

  File getFileById(long atchmnflId) throws Exception;
}
