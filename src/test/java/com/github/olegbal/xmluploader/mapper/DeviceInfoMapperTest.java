package com.github.olegbal.xmluploader.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.olegbal.xmluploader.domain.mappers.DeviceInfoMapperImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import com.github.olegbal.xmluploader.domain.dto.DeviceInfoDto;
import com.github.olegbal.xmluploader.domain.entity.DeviceInfo;
import com.github.olegbal.xmluploader.domain.mappers.DeviceInfoMapper;
import com.github.olegbal.xmluploader.exception.CantProcessDeviceInfoXmlException;

public class DeviceInfoMapperTest {

  private final DeviceInfoMapper deviceInfoMapper = new DeviceInfoMapperImpl();

  @Test
  void testMapToDeviceInfoDto() {
    DeviceInfo deviceInfo = new DeviceInfo(1L, 123, 456, 789, "testName", LocalDateTime.now(),
        "testFileName");

    DeviceInfoDto deviceInfoDto = deviceInfoMapper.toDeviceInfoDto(deviceInfo);

    assertEquals(deviceInfoDto.getId(), deviceInfo.getId());
    assertEquals(deviceInfoDto.getScreenWidth(), deviceInfo.getScreenWidth());
    assertEquals(deviceInfoDto.getScreenHeight(), deviceInfo.getScreenHeight());
    assertEquals(deviceInfoDto.getScreenDpi(), deviceInfo.getScreenDpi());
    assertEquals(deviceInfoDto.getNewspaperName(), deviceInfo.getNewspaperName());
    assertEquals(deviceInfoDto.getUploadDate(), deviceInfo.getUploadDate());
    assertEquals(deviceInfoDto.getFilename(), deviceInfo.getFilename());

  }

  @Test
  void testMapToDeviceInfo() {
    DeviceInfoDto deviceInfoDto = new DeviceInfoDto(1L, 123, 456, 789, "testName",
        LocalDateTime.now(),
        "testFileName");

    DeviceInfo deviceInfo = deviceInfoMapper.toDeviceInfo(deviceInfoDto);

    assertEquals(deviceInfoDto.getId(), deviceInfo.getId());
    assertEquals(deviceInfoDto.getScreenWidth(), deviceInfo.getScreenWidth());
    assertEquals(deviceInfoDto.getScreenHeight(), deviceInfo.getScreenHeight());
    assertEquals(deviceInfoDto.getScreenDpi(), deviceInfo.getScreenDpi());
    assertEquals(deviceInfoDto.getNewspaperName(), deviceInfo.getNewspaperName());
    assertEquals(deviceInfoDto.getUploadDate(), deviceInfo.getUploadDate());
    assertEquals(deviceInfoDto.getFilename(), deviceInfo.getFilename());

  }

  @Test
  void testMultiPartFileToDeviceInfo()
      throws IOException, ParserConfigurationException, SAXException {

    String fileName = "test.xml";
    File testXmlFile = ResourceUtils.getFile("classpath:xml/deviceinfo-valid.xml");

    MultipartFile multipartFile = new MockMultipartFile(fileName, fileName,
        MediaType.APPLICATION_XML_VALUE, new FileInputStream(testXmlFile));

    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    Document doc = builder.parse(new FileInputStream(testXmlFile));
    NamedNodeMap screenInfoAttributes = doc.getElementsByTagName("screenInfo").item(0)
        .getAttributes();
    String newspaperName = doc.getElementsByTagName("newspaperName").item(0).getTextContent();
    Integer width = Integer.valueOf(screenInfoAttributes.getNamedItem("width").getNodeValue());
    Integer height = Integer.valueOf(screenInfoAttributes.getNamedItem("height").getNodeValue());
    Integer dpi = Integer.valueOf(screenInfoAttributes.getNamedItem("dpi").getNodeValue());

    DeviceInfo deviceInfo = deviceInfoMapper.toDeviceInfoDto(multipartFile);

    assertDoesNotThrow(() -> new CantProcessDeviceInfoXmlException("Unable to parse received xml"));

    assertNull(deviceInfo.getId());
    assertEquals(width, deviceInfo.getScreenWidth());
    assertEquals(height, deviceInfo.getScreenHeight());
    assertEquals(dpi, deviceInfo.getScreenDpi());
    assertEquals(newspaperName, deviceInfo.getNewspaperName());
    assertNull(deviceInfo.getUploadDate());
    assertEquals(fileName, deviceInfo.getFilename());

  }

}
