package moe.msm;

import com.github.chhsiaoninety.nitmproxy.NitmProxy;
import com.github.chhsiaoninety.nitmproxy.NitmProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
public class NettyHijackProxy {

    private static final Logger logger = LoggerFactory.getLogger(NettyHijackProxy.class);

    private final int port;
    private final int redirectPort;
    private final List<String> redirectDomains;

    public NettyHijackProxy(int port, int redirectPort) {
        this.port = port;
        this.redirectPort = redirectPort;
        this.redirectDomains = Collections.emptyList();
    }

    public void start() throws Exception {
        var config = new NitmProxyConfig();
        config.setPort(this.port);
        config.setRedirectTargetPort(this.redirectPort);
        config.setRedirectDomains(this.redirectDomains);

        var proxy = new NitmProxy(config);
        proxy.start();
    }

    public static void main(String[] args) throws Exception {
        logger.info("Standalone mode running.");
        var config = new NitmProxyConfig();
        config.setPort(3456);
        config.setRedirectDomains(List.of("test.com"));

        var proxy = new NitmProxy(config);
        proxy.start();
    }
}
