package com.github.olegbal.xmluploader.controller;

import com.github.olegbal.xmluploader.domain.dto.DeviceInfoDto;
import com.github.olegbal.xmluploader.service.DeviceInfoService;
import com.github.olegbal.xmluploader.validation.annotation.ValidDeviceInfoXml;
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

@Validated
@RestController
public class DeviceInfoController {

  private final DeviceInfoService deviceInfoService;

  public DeviceInfoController(DeviceInfoService deviceInfoService) {
    this.deviceInfoService = deviceInfoService;
  }

  @GetMapping(value = "/api/v1/device-info", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<DeviceInfoDto>> getDeviceInfo(
      @RequestParam(required = false) String query, Pageable pageable) {
    Page<DeviceInfoDto> allDeviceInfo = deviceInfoService.getAllDeviceInfo(query, pageable);

    return new ResponseEntity<>(allDeviceInfo, HttpStatus.OK);
  }


  @PostMapping(value = "/api/v1/device-info", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Object> uploadDeviceInfo(
      @RequestParam("file") @ValidDeviceInfoXml MultipartFile file) {

    DeviceInfoDto createdDeviceInfoDto = deviceInfoService.createDeviceInfo(file);

    return new ResponseEntity<>(createdDeviceInfoDto, HttpStatus.CREATED);
  }
}
