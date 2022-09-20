package server;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerSpecRouterFactoryTest {

    @Test
    void itCreatesANewRouter() {
        Router router = new HTTPServerSpecRouterFactory().create();

        Router testRouter = new Router(
                Map.of("GET /simple_get", new SimpleGetHandler()),
                new NotFoundHandler()
        );

        assertEquals(testRouter.getClass(), router.getClass());
    }
}
