package org.openmrs.module.ugandaemr.tasks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.ugandaemr.api.UgandaEMRService;
import org.openmrs.scheduler.tasks.AbstractTask;


public class GenerateUniqueIdentifierCodeTask extends AbstractTask {
    private Log log = LogFactory.getLog(this.getClass());
    private UgandaEMRService ugandaemrService = Context.getService(UgandaEMRService.class);

    @Override
    public void execute() {
        log.info("method executing started");
        ugandaemrService.generateAndSaveUICForPatientsWithOut();
    }
}

