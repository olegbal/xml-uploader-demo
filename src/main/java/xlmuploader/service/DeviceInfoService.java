package xlmuploader.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import xlmuploader.domain.dto.DeviceInfoDto;

public interface DeviceInfoService {

  DeviceInfoDto updateDeviceInfo(DeviceInfoDto createDeviceInfoDto);

  DeviceInfoDto createDeviceInfo(MultipartFile xmlFile);

  Page<DeviceInfoDto> getAllDeviceInfo(Pageable pageable);
}
