package com.github.olegbal.xmluploader.validation;

import com.github.olegbal.xmluploader.validation.annotation.ValidXmlFile;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class XmlExtensionValidator implements ConstraintValidator<ValidXmlFile, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    return Objects.equals(value.getContentType(), MediaType.APPLICATION_XML_VALUE);
  }
}
