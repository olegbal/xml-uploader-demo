package xlmuploader.service;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xlmuploader.domain.dto.DeviceInfoDto;
import xlmuploader.domain.entity.DeviceInfo;
import xlmuploader.domain.mappers.DeviceInfoMapper;
import xlmuploader.repository.DeviceInfoRepository;

@Service
public class JpaDeviceInfoService implements DeviceInfoService {

  private final DeviceInfoRepository deviceInfoRepository;
  private final DeviceInfoMapper deviceInfoMapper;

  private final EntityManager entityManager;

  public JpaDeviceInfoService(DeviceInfoRepository deviceInfoRepository,
      DeviceInfoMapper deviceInfoMapper, EntityManager entityManager) {
    this.deviceInfoRepository = deviceInfoRepository;
    this.deviceInfoMapper = deviceInfoMapper;
    this.entityManager = entityManager;
  }

  @Override
  public DeviceInfoDto updateDeviceInfo(DeviceInfoDto deviceInfoDto) {
    DeviceInfo deviceInfo = deviceInfoMapper.toDeviceInfo(deviceInfoDto);

    return deviceInfoMapper.toDeviceInfoDto(deviceInfoRepository.save(deviceInfo));
  }

  @Override
  public DeviceInfoDto createDeviceInfo(MultipartFile xmlFile) {

    DeviceInfo deviceInfo = deviceInfoMapper.toDeviceInfoDto(xmlFile);

    deviceInfo.setId(null);
    deviceInfo.setUploadDate(LocalDateTime.now());

    return deviceInfoMapper.toDeviceInfoDto(deviceInfoRepository.save(deviceInfo));
  }

  @Override
  public Page<DeviceInfoDto> getAllDeviceInfo(String rsqlQuery, Pageable pageable) {
    if (!Objects.isNull(rsqlQuery)) {
      RSQLVisitor<CriteriaQuery<DeviceInfo>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
      Node rootNode = new RSQLParser().parse(rsqlQuery);
      CriteriaQuery<DeviceInfo> newQuery = rootNode.accept(visitor, entityManager);

      int pageNumber = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
      int pageSize = pageable.getPageSize();
      List<DeviceInfo> resultList = entityManager
          .createQuery(newQuery)
          .setFirstResult((pageNumber) * pageSize)
          .setMaxResults(pageSize)
          .getResultList();

      List<DeviceInfoDto> convertedResult = resultList.stream()
          .map(deviceInfoMapper::toDeviceInfoDto)
          .collect(Collectors.toList());

      return new PageImpl<>(convertedResult, pageable, deviceInfoRepository.count());
    } else {
      Page<DeviceInfo> resultPageable = deviceInfoRepository.findAll(pageable);

      return resultPageable.map(deviceInfoMapper::toDeviceInfoDto);
    }

  }
}
