package lits.jp.hotel.management.places;

import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("google")
@Slf4j
public class GooglePlacesSettings {

  private final Api api = new Api();

  public Api getApi() {
    return this.api;
  }

  public static class Api {

    @NotNull private String key;

    public String getGoogleKey() {
      return this.key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    @NotNull private String url;

    public String getGoogleUrl() {
      return this.url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}
