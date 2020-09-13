package com.dml.topup.service.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.exception.ErrorCode;
import com.dml.topup.util.SecurityUtil;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

/**
 * @author i.sadeghi
 */
@PropertySource("classpath:topup.properties")
public abstract class Service<T> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebClient webClient;

    @Autowired
    private Environment env;
    private Class<T> result;

    public Service(final Class<T> result) {
        this.result = result;
    }

    Environment getEnv() {
        return env;
    }

    Mono<T> callWebService(HttpMethod httpMethod, String url, Object body, UUID uuid) {
        Mono<T> response = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // Todo after version final, will be changed
        log.info("{"
                + "\"action\"" + ":" + "\"Setareyek\", "
                + "\"event\"" + ":" + "\"Request\", "
                + "\"uuid\"" + ":" + "\"" + uuid + "\", "
                + "\"httpMethod\"" + ":" + "\"" + httpMethod + "\", "
                + "\"url\"" + ":" + "\"" + url + "\", "
                + "\"body\"" + ":" + "\"" + body + "\", "
                + "\"spentTime\"" + ":" + stopWatch.getTime() + ", "
                + "\"result\"" + ":" + "\"" + result + "\" "
                + "}"
        );

        if (HttpMethod.GET.equals(httpMethod)) {
            response = webClient.get()
                    .uri(url)
                    .exchange()
                    .flatMap(clientResponse -> clientResponse.bodyToMono(result));
        } else if (HttpMethod.POST.equals(httpMethod)) {
            response = webClient.post()
                    .uri(url)
                    .body(Mono.just(body), Object.class)
                    .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(result);
        }
        // Todo after version final, will be changed
        log.info("{"
                + "\"action\"" + ":" + "\"Setareyek\", "
                + "\"event\"" + ":" + "\"Response\", "
                + "\"uuid\"" + ":" + "\"" + uuid + "\", "
                + "\"response\"" + ":" + "\"" + response.block().toString() + "\", "
                + "\"spentTime\"" + ":" + stopWatch.getTime() + ", "
                + "\"result\"" + ":" + "\"" + result + "\" "
                + "}"
        );
        stopWatch.stop();
        return response;
    }

    // Todo implementation spring AOP
    Response cheackAuthentication(String authorization) {
        Response response = new Response();
        if (!String.format(Constants.AUTHENTICATION_BASIC, SecurityUtil.userAuthorization).equals(String.format(Constants.DATA_HEADER_VALUE, authorization))) {
            response.setErrorCode(ErrorCode.AUTHORIZATION_EXCEPTION.getCode());
            response.setErrorDescription(ErrorCode.AUTHORIZATION_EXCEPTION.getDescription());
        }
        return response;
    }

    String createUrl(String key) {
        return String.format("%s%s", getEnv().getProperty(Constants.SETAREYEK_URL), getEnv().getProperty(key));
    }

    // Todo delete when applied from the client side
    int generateResNo() {
        int m = (int) Math.pow(10, 6 - 1);
        return m + new Random().nextInt(9 * m);
    }

    public static com.dml.topup.data.response.Page<DirectTopup> getDirectTopupPage(Page<DirectTopup> in) {
        com.dml.topup.data.response.Page<DirectTopup> out = new com.dml.topup.data.response.Page<>();
        out.setNumber(in.getNumber());
        out.setTotalElements(in.getTotalElements());
        out.setTotalPages(in.getTotalPages());
        out.setSize(in.getSize());
        out.setHasContent(in.hasContent());
        out.setFirst(in.isFirst());
        out.setLast(in.isLast());
        out.setNext(in.hasNext());
        out.setPrevious(in.hasPrevious());
        if (in.hasContent()) {
            out.setContent(in.getContent());
        }
        out.setNumberOfElements(in.getNumberOfElements());
        out.setContent(in.getContent());
        return out;
    }
}