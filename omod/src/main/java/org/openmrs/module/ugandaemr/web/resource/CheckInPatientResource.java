package org.openmrs.module.ugandaemr.web.resource;

import org.openmrs.api.AdministrationService;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.ProviderService;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientqueueing.model.PatientQueue;
import org.openmrs.module.ugandaemr.UgandaEMRConstants;
import org.openmrs.module.ugandaemr.api.UgandaEMRService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CheckInPatientResource {

    @RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/" + UgandaEMRConstants.UGANDAEMR_MODULE_ID
            + "/gp", method = RequestMethod.GET)

    public ResponseEntity<Object> evaluate(@RequestParam(required = true, value = "patient") String patient, @RequestParam(required = true, value = "currentLocation") String currentLocation, @RequestParam(required = true, value = "locationTo") String locationTo, @RequestParam(required = true, value = "queueRoom") String queueRoom, @RequestParam(required = true, value = "provider") String provider, String visitComment, String patientStatus, String visitType) {
        UgandaEMRService ugandaEMRService = Context.getService(UgandaEMRService.class);

        PatientService patientService = Context.getPatientService();
        LocationService locationService = Context.getLocationService();
        ProviderService providerService = Context.getProviderService();

        PatientQueue patientQueue = ugandaEMRService.checkInPatient(patientService.getPatientByUuid(patient), locationService.getLocation(currentLocation), locationService.getLocation(locationTo), locationService.getLocation(queueRoom), providerService.getProviderByUuid(provider), visitComment, patientStatus, visitType);


        if (patientQueue!=null) {
            Object facilityName = patientQueue;
            PropertyResult propertyResult = new PropertyResult(facilityName);
            List<PropertyResult> resultList = new ArrayList<>();
            resultList.add(propertyResult);

            ResultObject resultObject = new ResultObject(resultList);
            return ResponseEntity.ok().body(resultObject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    static class PropertyResult {
        private Object facilityName;

        public PropertyResult(Object facilityName) {
            this.facilityName = facilityName;
        }

        public Object getFacilityName() {
            return facilityName;
        }

        public void setFacilityName(Object facilityName) {
            this.facilityName = facilityName;
        }
    }

    static class ResultObject {
        private List<PropertyResult> results;

        public ResultObject(List<PropertyResult> results) {
            this.results = results;
        }

        public List<PropertyResult> getResults() {
            return results;
        }

        public void setResults(List<PropertyResult> results) {
            this.results = results;
        }
    }
}
