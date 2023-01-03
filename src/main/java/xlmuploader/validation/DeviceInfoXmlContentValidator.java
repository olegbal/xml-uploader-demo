package xlmuploader.validation;

import java.io.File;
import java.io.IOException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import xlmuploader.validation.annotation.ValidDeviceInfoXml;

public class DeviceInfoXmlContentValidator implements
    ConstraintValidator<ValidDeviceInfoXml, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    try {
      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      File xsdFile = ResourceUtils.getFile("classpath:schemas/deviceinfo.xsd");

      Schema schema = factory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(value.getInputStream()));
    } catch (IOException | SAXException e) {
      return false;
    }
    return true;
  }

}
