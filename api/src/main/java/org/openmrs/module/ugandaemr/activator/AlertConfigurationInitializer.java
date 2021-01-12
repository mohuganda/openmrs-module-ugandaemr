package org.openmrs.module.ugandaemr.activator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.module.ugandaemr.api.UgandaEMRService;

import static org.openmrs.module.ugandaemr.UgandaEMRConstants.*;
import static org.openmrs.module.ugandaemr.UgandaEMRConstants.GP_DHIS2_DEFAULT_ALERT_MESSAGE;
import static org.openmrs.module.ugandaemr.UgandaEMRConstants.GP_DHIS2_VALUE;

public class AlertConfigurationInitializer implements Initializer {
    protected Log log = LogFactory.getLog(AlertConfigurationInitializer.class);

    @Override
    public void started() {


        // set alert if National Health Provider Identifier is not set
        createAlertWhenDefaultIsNotSet(GP_NHPI, GP_NHPI_VALUE, GP_NHPI_DEFAULT_ALERT_MESSAGE);
        // set alert if Health Center Name is not set
        createAlertWhenDefaultIsNotSet(GP_HEALTH_CENTER_NAME, GP_HEALTH_CENTER_NAME_VALUE, GP_HEALTH_CENTER_NAME_DEFAULT_ALERT_MESSAGE);
        // set alert if DHIS2 Organization UUID is not set
        createAlertWhenDefaultIsNotSet(GP_DHIS2, GP_DHIS2_VALUE, GP_DHIS2_DEFAULT_ALERT_MESSAGE);
    }

    @Override
    public void stopped() {

    }

    /**
     * @param globalPropertyName
     * @param globalPropertyDefault
     * @param messageAlert
     */
    private void createAlertWhenDefaultIsNotSet(String globalPropertyName, String globalPropertyDefault, String messageAlert) {
        UgandaEMRService ugandaemrService = org.openmrs.api.context.Context.getService(UgandaEMRService.class);
        AdministrationService administrationService = org.openmrs.api.context.Context.getAdministrationService();
        GlobalProperty globalProperty = new GlobalProperty();
        globalProperty = administrationService.getGlobalPropertyObject(globalPropertyName);
        if (globalProperty.getPropertyValue() == null) {
            ugandaemrService.setAlertForAllUsers(messageAlert);
            log.info("Creating alert " + messageAlert);
        } else {
            if (globalProperty.getPropertyValue().equalsIgnoreCase(globalPropertyDefault)) {
                ugandaemrService.setAlertForAllUsers(messageAlert);
                log.info("Creating alert " + messageAlert);
            }
        }
    }
}
