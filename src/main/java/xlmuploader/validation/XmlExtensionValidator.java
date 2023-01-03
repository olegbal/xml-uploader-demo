package xlmuploader.validation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import xlmuploader.validation.annotation.ValidXmlFile;

public class XmlExtensionValidator implements ConstraintValidator<ValidXmlFile, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    return Objects.equals(value.getContentType(), MediaType.APPLICATION_XML_VALUE);
  }
}
