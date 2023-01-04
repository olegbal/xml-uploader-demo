package xlmuploader.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xlmuploader.domain.dto.DeviceInfoDto;
import xlmuploader.service.DeviceInfoService;
import xlmuploader.validation.annotation.ValidDeviceInfoXml;

@Validated
@RestController
public class DeviceInfoController {

  private final DeviceInfoService deviceInfoService;

  public DeviceInfoController(DeviceInfoService deviceInfoService) {
    this.deviceInfoService = deviceInfoService;
  }

  @GetMapping(value = "/api/v1/device-info", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<DeviceInfoDto>> getDeviceInfo(Pageable pageable) {
    Page<DeviceInfoDto> allDeviceInfo = deviceInfoService.getAllDeviceInfo(pageable);

    return new ResponseEntity<>(allDeviceInfo, HttpStatus.OK);
  }


  @PostMapping(value = "/api/v1/device-info", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Object> uploadDeviceInfo(
      @RequestParam("file") @ValidDeviceInfoXml MultipartFile file) {

    DeviceInfoDto createdDeviceInfoDto = deviceInfoService.createDeviceInfo(file);

    return new ResponseEntity<>(createdDeviceInfoDto, HttpStatus.CREATED);
  }
}
