package tinkering;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;

public class Tinker {
  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new BindingVerticle());

    JsonObject message = new JsonObject();
    message.put("collection", "mycollection")
      .put("document", new JsonObject().put("name", "tim"));
    DatabaseService databaseService = DatabaseService.createProxy(vertx, "serviceAddress");

    System.out.println(message);

    System.out.println(message.size());

    DeliveryOptions options = new DeliveryOptions().addHeader("action", "save");
    vertx.eventBus()
      .request("serviceAddress", message, options)
      .onSuccess(msg ->
      {
        System.out.println("yo bro we did it");
        // databaseService.save();
        // lets try to print the reply message received from the service
        System.out.println("Reply from service: " + msg.body());
      }).onFailure(msg2 ->
      {
        System.out.println("come on bro lets do it again");
      });

  }
}
