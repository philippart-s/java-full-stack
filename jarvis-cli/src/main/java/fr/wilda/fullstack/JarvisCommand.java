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
// 5.07-jarvis-annot
@Command(name = "jarvis", mixinStandardHelpOptions = true, subcommands={DevoxxSubCommand.class})
public class JarvisCommand implements Callable<Integer> {
    // Logger
    private static final Logger _LOG = LoggerFactory.getLogger(JarvisCommand.class);

    // 5.08-jarvis-rest-client
    @RestClient
    JarvisAPIService jarvisAPIService;

    // 5.09-jarvis-param
    @Parameters(paramLabel = "<name>", defaultValue = "Explique de manière concise ce que tu es et à quoi tu sers.", description = "Pose une question à Jarvis, et il te répondra.")
    String question;

    @Override
    public Integer call() throws Exception {
        _LOG.info("\n🤖:\n");

        // 5.10-call-service
        jarvisAPIService.askJarvis(question)
                .subscribe()
                .asStream()
                .forEach(token -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    _LOG.info(token);
                });
        _LOG.info("\n");

        return 0;
    }
}
