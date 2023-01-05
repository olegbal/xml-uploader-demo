package com.github.olegbal.xmluploader.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class XmlExtensionValidatorTest {

  private final XmlExtensionValidator validator = new XmlExtensionValidator();

  @Test
  void testValidFileIsTrue() throws IOException {
    File validXmlFile = ResourceUtils.getFile("classpath:xml/deviceinfo-valid.xml");
    MultipartFile multipartFile = new MockMultipartFile("test.xml", "test.xml",
        MediaType.APPLICATION_XML_VALUE, new FileInputStream(validXmlFile));

    assertTrue(validator.isValid(multipartFile, null));
  }

  @Test
  void testNotValidFileIsFalse() throws IOException {
    File validXmlFile = ResourceUtils.getFile("classpath:xml/deviceinfo-not-valid.xml");
    MultipartFile multipartFile = new MockMultipartFile("test.xml", "test.xml",
        MediaType.APPLICATION_JSON_VALUE, new FileInputStream(validXmlFile));

    assertFalse(validator.isValid(multipartFile, null));
  }
}
