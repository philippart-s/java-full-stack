package fr.wilda.fullstack;

import java.util.concurrent.Callable;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.wilda.fullstack.sdk.JarvisAPIService;
import jakarta.ws.rs.core.Response;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

// 5.11-devoxx-svc-annotation
@Command(name = "devoxx", mixinStandardHelpOptions = true)
public class DevoxxSubCommand implements Callable<Integer> {
  // Logger
  private static final Logger _LOG = LoggerFactory.getLogger(DevoxxSubCommand.class);

  // 5.12-devoxx-rest-client
  @RestClient
  JarvisAPIService jarvisAPIService;

  // 5.13-devoxx-options
  @Option(names = { "-es",
      "--embeddings-synchro" }, description = "Ajout des talks Devoxx dans la base d'embeddings")
  boolean embbedingSynchro;

  @Option(names = { "-ed",
      "--embeddings-deletion" }, description = "Suppression des talks Devoxx de la base d'embeddings")
  boolean embbedingDeletion;

  @Override
  public Integer call() throws Exception {

    // 5.14-devoxx-call
    if (embbedingSynchro) {
      if (jarvisAPIService.embeddingSynchro().getStatusInfo()
          .getFamily() != Response.Status.Family.SERVER_ERROR) {
        _LOG.info("🤖 : Synchronisation des talks Devoxx ✅.");
      } else {
        _LOG.info("🤖 : Une erreur est survenue lors de la synchronisation des talks Devoxx ❌.");
      }
    } else {
      if (embbedingDeletion) {
        if (jarvisAPIService.embeddingDeletion().getStatusInfo()
            .getFamily() != Response.Status.Family.SERVER_ERROR) {
          _LOG.info("🤖 : Suppression des talks Devoxx ✅.");
        } else {
          _LOG.info("🤖 : Une erreur est survenue lors de la suppression des talks Devoxx ❌.");
        }
      }
    }

    return 0;
  }
}
