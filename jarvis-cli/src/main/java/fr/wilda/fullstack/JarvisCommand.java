package fr.wilda.fullstack;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.wilda.fullstack.sdk.JarvisAPIService;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@TopCommand

public class JarvisCommand implements Callable<Integer> {
    // Logger
    private static final Logger _LOG = LoggerFactory.getLogger(JarvisCommand.class);

    // 5.08-jarvis-rest-client

    // 5.09-jarvis-param

    @Override
    public Integer call() throws Exception {
        _LOG.info("\n🤖:\n");

        // 5.10-call-service

        return 0;
    }
}
