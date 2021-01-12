package org.openmrs.module.ugandaemr.htmlformentry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.htmlformentry.CustomFormSubmissionAction;
import org.openmrs.module.htmlformentry.FormEntryContext.Mode;
import org.openmrs.module.htmlformentry.FormEntrySession;
import org.openmrs.module.ugandaemr.api.UgandaEMRService;


/**
 * Enrolls patients into DSDM programs
 */
public class TBEncounterPageSubmissionAction implements CustomFormSubmissionAction {

    private static final Log log = LogFactory.getLog(TBEncounterPageSubmissionAction.class);
    UgandaEMRService ugandaEMRService = Context.getService(UgandaEMRService.class);
    @Override
    public void applyAction(FormEntrySession session) {
        Mode mode = session.getContext().getMode();
        if (!(mode.equals(Mode.ENTER) || mode.equals(Mode.EDIT))) {
            return;
        }

        ugandaEMRService.processLabTestOrdersFromEncounterObs(session, true);

        ugandaEMRService.processDrugOrdersFromEncounterObs(session, true);

    }



}
