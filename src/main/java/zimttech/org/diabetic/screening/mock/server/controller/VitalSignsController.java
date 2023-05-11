package zimttech.org.diabetic.screening.mock.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zimttech.org.diabetic.screening.mock.server.entity.VitalSigns;
import zimttech.org.diabetic.screening.mock.server.service.VitalSignsService;

@RestController
@RequestMapping("/vital-signs")
public class VitalSignsController {

    private final VitalSignsService vitalSignsService;

    @Autowired
    public VitalSignsController(VitalSignsService vitalSignsService) {
        this.vitalSignsService = vitalSignsService;
    }

    @PostMapping
    public ResponseEntity<VitalSigns> saveVitalSign(@RequestBody VitalSigns vitalSigns) {
        VitalSigns savedVitalSign = vitalSignsService.saveVitalSign(vitalSigns);
        return ResponseEntity.ok(savedVitalSign);
    }
}
