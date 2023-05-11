package zimttech.org.diabetic.screening.mock.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zimttech.org.diabetic.screening.mock.server.entity.VitalSigns;
import zimttech.org.diabetic.screening.mock.server.service.VitalSignsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VitalSignsController {

    private final VitalSignsService vitalSignsService;

    @Autowired
    public VitalSignsController(VitalSignsService vitalSignsService) {
        this.vitalSignsService = vitalSignsService;
    }

    @PostMapping("/vitals")
    public ResponseEntity<List<VitalSigns>> saveVitalSign(@RequestBody List<VitalSigns> vitalSigns) {
        List<VitalSigns> savedVitalSign = vitalSignsService.saveVitalSigns(vitalSigns);
        return ResponseEntity.ok(savedVitalSign);
    }
}
