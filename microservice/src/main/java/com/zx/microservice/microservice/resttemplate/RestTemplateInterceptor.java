package com.zx.microservice.microservice.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhouxin
 * @date 2018-12-15
 */
@Configuration
@EnableScheduling
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

  private final DiscoveryClient discoveryClient;

  private volatile Map<String, Set<String>> urls = new HashMap<>();

  public RestTemplateInterceptor(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  @Scheduled(fixedDelay = 10 * 1000 * 60)
  public void updateTagUrls() {
    Map<String, Set<String>> newUrls = new HashMap<>();
    discoveryClient.getServices().forEach(serviceName -> {
      Set<String> collect = discoveryClient.getInstances(serviceName).stream()
          .map(
              e -> e.isSecure() ? "https://" + e.getHost() + ":" + e.getPort()
                  : "http://" + e.getHost() + ":" + e.getPort()).collect(Collectors.toSet());
      newUrls.put(serviceName, collect);
    });
    Map<String, Set<String>> oldUrls = this.urls;
    this.urls = newUrls;
    oldUrls.clear();
    logger.info("--------->RestTemplateInterceptor#updateTagUrls()|newUrls:{}", newUrls);
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    URI uri = request.getURI();
    ArrayList<String> thisUrls = new ArrayList<>(urls.get(uri.getHost()));
    int index = new Random().nextInt(thisUrls.size());
    String host = "https://" + uri.getHost();
    if (uri.toString().startsWith("https")) {
      host = "https://" + uri.getHost();
    }
    String path = uri.toString().replace(host, thisUrls.get(index));
    URLConnection urlConnection = new URL(path).openConnection();
    urlConnection.setDoOutput(true);
    HttpHeaders headers = request.getHeaders();
    /**
     * 如果 Content-Length 为0
     * {@link org.springframework.web.client.MessageBodyClientHttpResponseWrapper#hasMessageBody}
     * 判断成功 getHeaders().getContentLength() == 0
     * 直接返回null
     */
    headers.set("Content-Length", "1");
    return new ZXClientHttpResponse(urlConnection.getInputStream(), new HttpHeaders());
  }

  class ZXClientHttpResponse implements ClientHttpResponse {

    private InputStream inputStream;

    private HttpHeaders httpHeaders;

    ZXClientHttpResponse(InputStream inputStream, HttpHeaders httpHeaders) {
      this.httpHeaders = httpHeaders;
      this.inputStream = inputStream;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
      return HttpStatus.OK;
    }

    @Override
    public int getRawStatusCode() throws IOException {
      return 200;
    }

    @Override
    public String getStatusText() throws IOException {
      return "OK";
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
      return inputStream;
    }

    @Override
    public HttpHeaders getHeaders() {
      return httpHeaders;
    }
  }
}
