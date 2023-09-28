package in.yogakshema.income;

import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDMNRuntimeEventListener implements DMNRuntimeEventListener {


    private static final Logger LOG = LoggerFactory.getLogger(LoggingDMNRuntimeEventListener.class);

    @Override
    public void beforeEvaluateDecision(BeforeEvaluateDecisionEvent event) {
        LOG.info("BeforeEvaluateDecisionEvent: {}", event);
    }

    @Override
    public void afterEvaluateDecision(AfterEvaluateDecisionEvent event) {
        event.getResult().getDecisionResults().stream().map(DMNDecisionResult::getResult).forEach(r -> LOG.info(r.toString()));
    }

    @Override
    public void beforeEvaluateContextEntry(BeforeEvaluateContextEntryEvent event) {
        LOG.info("BeforeEvaluateContextEntryEvent: {}", event);
    }

    @Override
    public void afterEvaluateContextEntry(AfterEvaluateContextEntryEvent event) {
        LOG.info("AfterEvaluateContextEntryEvent: {}", event);
    }

    @Override
    public void beforeEvaluateDecisionTable(BeforeEvaluateDecisionTableEvent event) {
        LOG.info("BeforeEvaluateDecisionTableEvent: {}", event);
    }

    @Override
    public void afterEvaluateDecisionTable(AfterEvaluateDecisionTableEvent event) {
        LOG.info("AfterEvaluateDecisionTableEvent: {}", event);
    }

    @Override
    public void beforeEvaluateAll(BeforeEvaluateAllEvent event) {
        LOG.info("BeforeEvaluateAllEvent: {}", event);
    }

    @Override
    public void afterEvaluateAll(AfterEvaluateAllEvent event) {
        LOG.info("AfterEvaluateAllEvent: {}", event);
    }
}
