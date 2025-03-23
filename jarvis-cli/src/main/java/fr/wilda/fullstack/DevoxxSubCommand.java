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
public class DevoxxSubCommand implements Callable<Integer> {
  // Logger
  private static final Logger _LOG = LoggerFactory.getLogger(DevoxxSubCommand.class);

  // 5.12-devoxx-rest-client

  // 5.13-devoxx-options

  @Override
  public Integer call() throws Exception {

    // 5.14-devoxx-call

    return 0;
  }
}
