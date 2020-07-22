# Netty Hijack Proxy

[![](https://jitpack.io/v/samnyan/netty-hijack-proxy.svg)](https://jitpack.io/#samnyan/netty-hijack-proxy)

An HTTP/HTTPS proxy base on [nitmproxy](https://github.com/chhsiao90/nitmproxy).

Provide features like redirecting specific host to a local http server.

## Get with JitPack

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.samnyan:netty-hijack-proxy:master-SNAPSHOT'
	}
```

## How to use

Initialize a `NettyHijackProxy` instance with `NitmProxyConfig`, then call the `start()` method.

The most important parameter is:

* host - The proxy server listen at. Default: "127.0.0.1"

* port - The proxy server port listen at. Default: 8080

* certFile - The ssl cert path. Default: "server.pem"

* keyFile - The ssl key path. Default: "key.pem"

* redirectDomains - The list of redirect domains. Default: empty list

* redirectTargetHost - The redirect target host. Default: "127.0.0.1"

* redirectTargetHttpPort - The redirect target http port. Default: 3456

* redirectTargetHttpsPort - The redirect target https port. Default: 3457

* insecure - Disable remote certificate checking. Default: false (This is insecure, should implement a custom keystore loader in the future)

For generating the root cert and key, check [here](https://jaanus.com/ios-13-certificates/)

## Example

[Check here](https://github.com/samnyan/DMTQ-Localserver/blob/master/gameserver/src/main/java/moe.msm.dmtqserver/)