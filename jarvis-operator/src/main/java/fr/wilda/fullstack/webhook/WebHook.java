package fr.wilda.fullstack.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.wilda.fullstack.JarvisOperatorReconciler;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/webhook")
public class WebHook {
  private static final Logger _LOG = LoggerFactory.getLogger(WebHook.class);

  // 4.24-inject-jarvis-operator

  // 4.25-add-new-tag-method

}