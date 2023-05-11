package zimttech.org.diabetic.screening.mock.server.service;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zimttech.org.diabetic.screening.mock.server.entity.VitalSigns;
import zimttech.org.diabetic.screening.mock.server.entity.enums.VitalSignsType;
import zimttech.org.diabetic.screening.mock.server.repository.VitalSignsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EtlService {

    private final VitalSignsRepository vitalSignsRepository;
    private final GrafanaClient grafanaClient;

    @Autowired
    public EtlService(VitalSignsRepository vitalSignsRepository, GrafanaClient grafanaClient) {
        this.vitalSignsRepository = vitalSignsRepository;
        this.grafanaClient = grafanaClient;
    }
    public void etlDataToGrafana() {
        // Retrieve data from the database
        List<VitalSigns> dataList = vitalSignsRepository.findAll();

        // Transform the data into a format that can be pushed to Grafana
        List<Map<String, Object>> transformedData = dataList.stream()
                .map(data -> {
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("id", data.getId());
                    dataMap.put("patient_id", data.getPatientId());
                    dataMap.put("vital_sign_type", data.getVitalSignsType());
                    dataMap.put("date", data.getLocalDateOfVitalSign());
                    dataMap.put("value", data.getCurrentValue());
                    dataMap.put("unit", data.getUnitOfMeasurement());
                    return dataMap;
                })
                .collect(Collectors.toList());

        // Push the data to Grafana
        GrafanaClient grafanaClient = new GrafanaClient();
        grafanaClient.pushDataToGrafana("data", transformedData);
    }
}
