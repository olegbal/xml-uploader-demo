package xlmuploader.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xlmuploader.domain.dto.DeviceInfoDto;

public interface DeviceInfoService {

  DeviceInfoDto updateDeviceInfo(DeviceInfoDto createDeviceInfoDto);

  DeviceInfoDto createDeviceInfo(MultipartFile xmlFile);

  Page<DeviceInfoDto> getAllDeviceInfo(String rsqlQuery, Pageable pageable);

}
