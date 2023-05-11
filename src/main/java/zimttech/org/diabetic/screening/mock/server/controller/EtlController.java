package zimttech.org.diabetic.screening.mock.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zimttech.org.diabetic.screening.mock.server.service.EtlService;

@RestController
public class EtlController {
    private final EtlService etlService;

    @Autowired
    public EtlController(EtlService etlService) {
        this.etlService = etlService;
    }

    @PostMapping("/etl/vital-signs")
    public void etlVitalSignsDataToSuperset() {
        etlService.etlDataToGrafana();
    }
}
