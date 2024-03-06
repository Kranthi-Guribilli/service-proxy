package tinkering;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

public class DatabaseServiceImpl implements DatabaseService {

  private final Vertx vertx;

  public DatabaseServiceImpl(Vertx vertx) {
    this.vertx = vertx;
    registerConsumer();
  }

  private void registerConsumer() {
    MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("serviceAddress");
    consumer.handler(message -> {
      JsonObject document = message.body();
      save(document);
      message.reply("Document saved successfully!");
    });
  }

  private void save(JsonObject document) {
    //saving data by printing to console ;)
    System.out.println("Saving document: " + document.encodePrettily());
  }

  @Override
  public void save() {
    // not used in this implementation
  }
}
