package zimttech.org.diabetic.screening.mock.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zimttech.org.diabetic.screening.mock.server.entity.VitalSigns;

public interface VitalSignsRepository extends JpaRepository<VitalSigns, String> {
}
