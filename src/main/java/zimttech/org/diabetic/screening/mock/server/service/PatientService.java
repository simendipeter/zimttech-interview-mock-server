package zimttech.org.diabetic.screening.mock.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimttech.org.diabetic.screening.mock.server.entity.Patient;
import zimttech.org.diabetic.screening.mock.server.entity.VitalSigns;
import zimttech.org.diabetic.screening.mock.server.entity.enums.VitalSignsType;
import zimttech.org.diabetic.screening.mock.server.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
