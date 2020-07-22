package moe.msm;

import com.github.chhsiaoninety.nitmproxy.NitmProxy;
import com.github.chhsiaoninety.nitmproxy.NitmProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        List<String> redirectList = new ArrayList<>();
        redirectList.add("pmang.com");
        redirectList.add("pmangplus.com");
        redirectList.add("neonapi.com");
        NitmProxyConfig proxyConfig = new NitmProxyConfig();
        proxyConfig.setHost("0.0.0.0");
        proxyConfig.setPort(3458);
        proxyConfig.setInsecure(true);
        proxyConfig.setRedirectDomains(redirectList);
        proxyConfig.setRedirectTargetHost("127.0.0.1");
        proxyConfig.setRedirectTargetHttpPort(3456);
        proxyConfig.setRedirectTargetHttpsPort(3457);

        NitmProxy proxy = new NitmProxy(proxyConfig);
        proxy.start();
    }
}
