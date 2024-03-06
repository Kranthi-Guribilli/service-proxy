package tinkering;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;

@ProxyGen
@VertxGen
public interface DatabaseService {

  static DatabaseService create(Vertx vertx) {
    return new DatabaseServiceImpl(vertx);
  }

  static DatabaseService createProxy(Vertx vertx, String address) {
    return new DatabaseServiceVertxEBProxy(vertx, address);
  }
  //void save(String collection, JsonObject document);
  void save();
}
