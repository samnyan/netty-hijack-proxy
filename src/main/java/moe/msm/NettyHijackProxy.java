package moe.msm;

import com.github.chhsiaoninety.nitmproxy.NitmProxy;
import com.github.chhsiaoninety.nitmproxy.NitmProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
public class NettyHijackProxy {

    private static final Logger logger = LoggerFactory.getLogger(NettyHijackProxy.class);

    private final NitmProxyConfig config;

    public NettyHijackProxy(NitmProxyConfig config) {
        this.config = config;
    }

    public void start() throws Exception {
        NitmProxy proxy = new NitmProxy(config);
        proxy.start();
    }

    public static void main(String[] args) throws Exception {
        logger.info("Standalone mode running.");
        NitmProxyConfig config = new NitmProxyConfig();
        config.setPort(3456);

        NitmProxy proxy = new NitmProxy(config);
        proxy.start();
    }
}
