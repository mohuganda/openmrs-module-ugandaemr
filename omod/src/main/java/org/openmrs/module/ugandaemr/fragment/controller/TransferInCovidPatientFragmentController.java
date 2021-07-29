package org.openmrs.module.ugandaemr.fragment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

public class TransferInCovidPatientFragmentController {

    Log log = LogFactory.getLog(TransferInCovidPatientFragmentController.class);

    public TransferInCovidPatientFragmentController() {

    }

    public void processCovidPatient(FragmentModel model,
                                     @RequestParam(value = "patientDataFromRDS", required = false) java.lang.String patientDataFromRDS, UiUtils ui) {
        log.info(patientDataFromRDS);
        JSONObject jsonObject = new JSONObject(patientDataFromRDS);
        //log.info(jsonObject);
        JSONObject patientData = (JSONObject) jsonObject.get("patientData");
        //log.info("show data" + patientData);




    }
}
