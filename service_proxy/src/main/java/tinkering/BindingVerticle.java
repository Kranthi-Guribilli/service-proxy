package tinkering;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.serviceproxy.ServiceBinder;

public class BindingVerticle extends AbstractVerticle {

  private DatabaseService databaseService;

  @Override
  public void start(Promise<Void> promise) {
    // Initialize and register the DatabaseService
    databaseService = DatabaseService.create(vertx);
    new ServiceBinder(vertx)
      .setAddress("serviceAddress")
      .register(DatabaseService.class, databaseService);
    promise.complete();
  }
}
