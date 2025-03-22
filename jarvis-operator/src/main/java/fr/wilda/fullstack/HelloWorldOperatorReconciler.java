package fr.wilda.fullstack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javaoperatorsdk.operator.api.reconciler.Cleaner;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.DeleteControl;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;

public class HelloWorldOperatorReconciler implements Reconciler<HelloWorldOperator>, Cleaner<HelloWorldOperator> {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldOperatorReconciler.class);

    @Override
    public UpdateControl<HelloWorldOperator> reconcile(HelloWorldOperator resource,
            Context<HelloWorldOperator> context) {
        // 4.07-log-hw-reconcile                
        log.info("👋 Hello, World 🌏! From {} ", resource.getSpec().getName());

        return UpdateControl.noUpdate();
    }

    // 4.08-add-cleanup-hw-reconcile
    @Override
    public DeleteControl cleanup(HelloWorldOperator resource, Context<HelloWorldOperator> context) throws Exception {
        log.info("🥲  Goodbye, World 🌏! From {}", resource.getSpec().getName());

        return DeleteControl.defaultDelete();
    }
}