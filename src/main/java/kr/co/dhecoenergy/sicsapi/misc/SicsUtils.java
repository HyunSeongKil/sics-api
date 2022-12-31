package kr.co.dhecoenergy.sicsapi.misc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;

public class SicsUtils {

  /**
   * dto 필드를 기준으로 entity의 값을 dto로 복사
   * 
   * @param <ENTITY>
   * @param <DTO>
   * @param entityObj
   * @param dtoClass
   * @return
   */
  public static <ENTITY, DTO> DTO entityToDto(ENTITY entityObj, Class<DTO> dtoClass) {
    try {
      DTO dtoObj = CmmnBeanUtils.newInstance(dtoClass);

      Field[] dtoFields = dtoClass.getDeclaredFields();

      for (Field f : dtoFields) {
        f.setAccessible(true);
        String fieldName = f.getName();

        if (!existsField(dtoObj, fieldName)) {
          continue;
        }

        Object fieldValue = CmmnBeanUtils.getFieldValue(dtoObj, fieldName);
        f.set(dtoObj, fieldValue);
      }

      return dtoObj;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * entity 필드를 기준으로 dto의 값을 entity로 복사
   * 
   * @param <DTO>
   * @param <ENTITY>
   * @param dtoObj
   * @param entityClass
   * @return
   */
  public static <DTO, ENTITY> ENTITY dtoToEntity(DTO dtoObj, Class<ENTITY> entityClass) {

    try {
      ENTITY entityObj = CmmnBeanUtils.newInstance(entityClass);

      Field[] entityFields = entityClass.getDeclaredFields();

      for (Field f : entityFields) {
        f.setAccessible(true);
        String fieldName = f.getName();

        if ("registDt".equals(fieldName)) {
          f.set(entityObj, new Date());
        }

        if (!existsField(dtoObj, fieldName)) {
          continue;
        }

        Object fieldValue = CmmnBeanUtils.getFieldValue(dtoObj, fieldName);
        f.set(entityObj, fieldValue);
      }

      return entityObj;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * obj에 fieldName의 필드가 존재하는지 검사
   * 
   * @param obj
   * @param fieldName
   * @return
   */
  public static boolean existsField(Object obj, String fieldName) {
    try {
      return null != obj.getClass().getDeclaredField(fieldName);
    } catch (NoSuchFieldException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }
}
