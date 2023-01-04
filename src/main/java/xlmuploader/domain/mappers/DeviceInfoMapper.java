package xlmuploader.domain.mappers;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import xlmuploader.domain.dto.DeviceInfoDto;
import xlmuploader.domain.entity.DeviceInfo;
import xlmuploader.exception.CantProcessDeviceInfoXmlException;

@Mapper(componentModel = "spring")
public interface DeviceInfoMapper {

  DeviceInfoDto toDeviceInfoDto(DeviceInfo deviceInfo);

  DeviceInfo toDeviceInfo(
      DeviceInfoDto identifiedDeviceInfoDto);

  default DeviceInfo toDeviceInfoDto(MultipartFile xmlFile) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(xmlFile.getInputStream());

      NamedNodeMap screenInfoAttributes = doc.getElementsByTagName("screenInfo").item(0)
          .getAttributes();
      String newspaperName = doc.getElementsByTagName("newspaperName").item(0).getTextContent();

      Integer width = Integer.valueOf(screenInfoAttributes.getNamedItem("width").getNodeValue());
      Integer height = Integer.valueOf(screenInfoAttributes.getNamedItem("height").getNodeValue());
      Integer dpi = Integer.valueOf(screenInfoAttributes.getNamedItem("dpi").getNodeValue());
      String fileName = xmlFile.getOriginalFilename();

      return new DeviceInfo(null, width, height, dpi, newspaperName,
          null, fileName);
    } catch (SAXException | IOException | ParserConfigurationException ex) {
      throw new CantProcessDeviceInfoXmlException("Unable to parse received xml");
    }

  }


}
