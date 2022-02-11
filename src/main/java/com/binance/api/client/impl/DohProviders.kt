package com.binance.api.client.impl

import okhttp3.Cache
import okhttp3.Dns
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.dnsoverhttps.DnsOverHttps
import java.io.File
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class DohProviders: Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        try {
            val appCache = Cache(File("cacheDir", "okhttpcache"), 10 * 1024 * 1024)
            val bootstrapClient = OkHttpClient.Builder()
                .cache(appCache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .dohCloudflare()
                .dohAdGuard()
                .dohGoogle()
                .build()
            return bootstrapClient.dns.lookup(hostname)
        } catch (e: NullPointerException) {
            throw UnknownHostException("Broken system behaviour for dns lookup of $hostname").apply {
                initCause(e)
            }
        }
    }

}

fun OkHttpClient.Builder.dohCloudflare() = dns(
    DnsOverHttps.Builder().client(build())
        .url("https://cloudflare-dns.com/dns-query".toHttpUrl())
        .bootstrapDnsHosts(
            InetAddress.getByName("162.159.36.1"),
            InetAddress.getByName("162.159.46.1"),
            InetAddress.getByName("1.1.1.1"),
            InetAddress.getByName("1.0.0.1"),
            InetAddress.getByName("162.159.132.53"),
            InetAddress.getByName("2606:4700:4700::1111"),
            InetAddress.getByName("2606:4700:4700::1001"),
            InetAddress.getByName("2606:4700:4700::0064"),
            InetAddress.getByName("2606:4700:4700::6400")
        )
        .build()
)

fun OkHttpClient.Builder.dohGoogle() = dns(
    DnsOverHttps.Builder().client(build())
        .url("https://dns.google/dns-query".toHttpUrl())
        .bootstrapDnsHosts(
            InetAddress.getByName("8.8.4.4"),
            InetAddress.getByName("8.8.8.8")
        )
        .build()
)

// AdGuard "Default" DNS works too but for the sake of making sure no site is blacklisted, i picked "Unfiltered"
fun OkHttpClient.Builder.dohAdGuard() = dns(
    DnsOverHttps.Builder().client(build())
        .url("https://dns-unfiltered.adguard.com/dns-query".toHttpUrl())
        .bootstrapDnsHosts(
            InetAddress.getByName("94.140.14.140"),
            InetAddress.getByName("94.140.14.141"),
            InetAddress.getByName("2a10:50c0::1:ff"),
            InetAddress.getByName("2a10:50c0::2:ff"),
        )
        .build()
)