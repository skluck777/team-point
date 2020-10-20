package mileage;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PointRepository extends PagingAndSortingRepository<Point, Long>{
    Optional<Point> findByMemberId(Long orderId);

}