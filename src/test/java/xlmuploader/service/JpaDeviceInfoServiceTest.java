package xlmuploader.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import xlmuploader.domain.dto.DeviceInfoDto;
import xlmuploader.domain.entity.DeviceInfo;
import xlmuploader.domain.mappers.DeviceInfoMapper;
import xlmuploader.repository.DeviceInfoRepository;


@ExtendWith(SpringExtension.class)
class JpaDeviceInfoServiceTest {


  @Mock
  private DeviceInfoRepository deviceInfoRepository;
  @Mock
  private DeviceInfoMapper deviceInfoMapper;
  @Mock
  private EntityManager entityManager;

  private DeviceInfoService deviceInfoService;

  @BeforeEach
  void initComponents() {
    this.deviceInfoService = new JpaDeviceInfoService(deviceInfoRepository, deviceInfoMapper,
        entityManager);
  }

  @Test
  void testUpdateDeviceInfo() {
    LocalDateTime now = LocalDateTime.now();
    DeviceInfo deviceInfo = new DeviceInfo(1L, 123, 456, 789, "testName", now, "testFileName");
    DeviceInfoDto deviceInfoDto = new DeviceInfoDto(1L, 123, 456, 789, "testName", now,
        "testFileName");

    when(deviceInfoRepository.save(deviceInfo)).thenReturn(deviceInfo);
    when(deviceInfoMapper.toDeviceInfoDto(deviceInfo)).thenReturn(deviceInfoDto);
    when(deviceInfoMapper.toDeviceInfo(deviceInfoDto)).thenReturn(deviceInfo);

    DeviceInfoDto resultDto = deviceInfoService.updateDeviceInfo(deviceInfoDto);

    assertEquals(resultDto, deviceInfoDto);
    assertEquals(resultDto.getId(), deviceInfo.getId());
    assertEquals(resultDto.getScreenWidth(), deviceInfo.getScreenWidth());
    assertEquals(resultDto.getScreenHeight(), deviceInfo.getScreenHeight());
    assertEquals(resultDto.getScreenDpi(), deviceInfo.getScreenDpi());
    assertEquals(resultDto.getNewspaperName(), deviceInfo.getNewspaperName());
    assertEquals(resultDto.getUploadDate(), deviceInfo.getUploadDate());
    assertEquals(resultDto.getFilename(), deviceInfo.getFilename());

    verify(deviceInfoRepository).save(any());
    verify(deviceInfoMapper).toDeviceInfoDto(deviceInfo);
    verify(deviceInfoMapper).toDeviceInfo(deviceInfoDto);

  }

  @Test
  void testCreateDeviceInfo() {
    LocalDateTime now = LocalDateTime.now();
    DeviceInfo deviceInfo = new DeviceInfo(null, 123, 456, 789, "testName", now, "testFileName");
    DeviceInfoDto deviceInfoDto = new DeviceInfoDto(1L, 123, 456, 789, "testName", now,
        "testFileName");
    MultipartFile mockMultipartFile = new MockMultipartFile("test.xml", new byte[]{});

    when(deviceInfoMapper.toDeviceInfoDto(mockMultipartFile)).thenReturn(deviceInfo);
    when(deviceInfoRepository.save(deviceInfo)).thenReturn(deviceInfo);
    when(deviceInfoMapper.toDeviceInfoDto(deviceInfo)).thenReturn(deviceInfoDto);

    DeviceInfoDto resultDto = deviceInfoService.createDeviceInfo(mockMultipartFile);

    assertNotNull(resultDto.getId());
    assertEquals(resultDto.getScreenWidth(), deviceInfoDto.getScreenWidth());
    assertEquals(resultDto.getScreenHeight(), deviceInfoDto.getScreenHeight());
    assertEquals(resultDto.getScreenDpi(), deviceInfoDto.getScreenDpi());
    assertEquals(resultDto.getNewspaperName(), deviceInfoDto.getNewspaperName());
    assertEquals(resultDto.getUploadDate(), deviceInfoDto.getUploadDate());
    assertEquals(resultDto.getFilename(), deviceInfoDto.getFilename());

    verify(deviceInfoRepository).save(any());
    verify(deviceInfoMapper).toDeviceInfoDto(mockMultipartFile);
    verify(deviceInfoMapper).toDeviceInfoDto(deviceInfo);
  }

  @Test
  void testGetAllDeviceInfo() {
    LocalDateTime now = LocalDateTime.now();

    PageRequest pageRequest = PageRequest.of(0, 3);
    String query = null;

    PageImpl<DeviceInfo> deviceInfoResultPage = new PageImpl<>(
        List.of(
            new DeviceInfo(1L, 123, 456, 789, "testName", now, "testFileName"),
            new DeviceInfo(2L, 123, 456, 789, "testName", now, "testFileName"),
            new DeviceInfo(3L, 123, 456, 789, "testName", now, "testFileName")
        ),
        pageRequest, 3);

    when(deviceInfoRepository.findAll(pageRequest)).thenReturn(deviceInfoResultPage);
    when(deviceInfoMapper.toDeviceInfoDto(any(DeviceInfo.class))).thenReturn(
        any(DeviceInfoDto.class));

    Page<DeviceInfoDto> resultPage = deviceInfoService.getAllDeviceInfo(query, pageRequest);

    assertEquals(resultPage.getSize(), deviceInfoResultPage.getSize());

    verify(deviceInfoRepository).findAll(pageRequest);
    verify(deviceInfoMapper, times(3)).toDeviceInfoDto(any(DeviceInfo.class));

  }
}