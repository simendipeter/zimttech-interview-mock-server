package zimttech.org.diabetic.screening.mock.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimttech.org.diabetic.screening.mock.server.entity.VitalSigns;
import zimttech.org.diabetic.screening.mock.server.repository.VitalSignsRepository;

@Service
@Slf4j
public class VitalSignsService {

    private final VitalSignsRepository vitalSignsRepository;

    @Autowired
    public VitalSignsService(VitalSignsRepository vitalSignsRepository) {
        this.vitalSignsRepository = vitalSignsRepository;
    }

    public VitalSigns saveVitalSign(VitalSigns vitalSigns){
        return vitalSignsRepository.save(vitalSigns);
    }

}
