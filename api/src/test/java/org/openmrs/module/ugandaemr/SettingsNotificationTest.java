package org.openmrs.module.ugandaemr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.ugandaemr.api.UgandaEMRService;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import static org.openmrs.module.ugandaemr.UgandaEMRConstants.*;

public class SettingsNotificationTest extends BaseModuleContextSensitiveTest {

    @Before
    public void setGlobalProperty() {
        GlobalProperty globalProperty = new GlobalProperty();
        GlobalProperty globalProperty1 = new GlobalProperty();

        globalProperty.setProperty(GP_NHPI);
        globalProperty.setPropertyValue(GP_NHPI_VALUE);
        globalProperty.setDescription(GP_NHPI_DESCRIPTION);

        AdministrationService administrationService = Context.getAdministrationService();
        administrationService.saveGlobalProperty(globalProperty);

    }

    @Test
    public void shouldReturnSetGlobalProperty() {
        AdministrationService administrationService = Context.getAdministrationService();
        Assert.assertEquals(administrationService.getGlobalProperty(GP_NHPI), GP_NHPI_VALUE);
    }

    @Test
    public void shouldSaveAlert() {
        AdministrationService administrationService = Context.getAdministrationService();
        UgandaEMRService ugandaemrService = Context.getService(UgandaEMRService.class);
        if (administrationService.getGlobalProperty(GP_NHPI) == GP_NHPI_VALUE) {
            ugandaemrService.setAlertForAllUsers("This is a message");
        }
    }
}
