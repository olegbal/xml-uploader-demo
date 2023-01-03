package xlmuploader.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import xlmuploader.domain.entity.DeviceInfo;

@Repository
public interface DeviceInfoRepository extends PagingAndSortingRepository<DeviceInfo, Long> {

}
