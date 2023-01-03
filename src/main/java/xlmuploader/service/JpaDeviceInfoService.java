package xlmuploader.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import xlmuploader.domain.dto.DeviceInfoDto;
import xlmuploader.domain.entity.DeviceInfo;
import xlmuploader.domain.mappers.DeviceInfoMapper;
import xlmuploader.repository.DeviceInfoRepository;

@Service
public class JpaDeviceInfoService implements DeviceInfoService {

  private final DeviceInfoRepository deviceInfoRepository;
  private final DeviceInfoMapper deviceInfoMapper;

  public JpaDeviceInfoService(DeviceInfoRepository deviceInfoRepository,
      DeviceInfoMapper deviceInfoMapper) {
    this.deviceInfoRepository = deviceInfoRepository;
    this.deviceInfoMapper = deviceInfoMapper;
  }

  @Override
  public DeviceInfoDto updateDeviceInfo(DeviceInfoDto deviceInfoDto) {
    DeviceInfo deviceInfo = deviceInfoMapper.toDeviceInfo(deviceInfoDto);

    return deviceInfoMapper.toDeviceInfoDto(deviceInfoRepository.save(deviceInfo));
  }

  @Override
  public DeviceInfoDto createDeviceInfo(MultipartFile xmlFile) {

    DeviceInfo deviceInfo = deviceInfoMapper.toDeviceInfoDto(xmlFile);

    return deviceInfoMapper.toDeviceInfoDto(deviceInfoRepository.save(deviceInfo));
  }

  @Override
  public Page<DeviceInfoDto> getAllDeviceInfo(Pageable pageable) {
    Page<DeviceInfo> resultPageable = deviceInfoRepository.findAll(pageable);

    return resultPageable.map(deviceInfoMapper::toDeviceInfoDto);
  }
}
