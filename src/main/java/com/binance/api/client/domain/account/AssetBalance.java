package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An asset balance in an Account.
 *
 * @see Account
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetBalance {

    /**
     * Asset symbol.
     */
    private String asset;

    /**
     * Available balance.
     */
    private String free;

    /**
     * Locked by open orders.
     */
    private String locked;

    private String withdrawing;

    private String btcValuation;

    private String fiatValuation;

    private String assetName;

    private String logoUrl;

    private String freeze;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("asset", asset)
                .append("assetName", assetName)
                .append("logoUrl", logoUrl)
                .append("free", free)
                .append("locked", locked)
                .append("freeze", freeze)
                .append("withdrawing", withdrawing)
                .append("btcValuation", btcValuation)
                .append("fiatValuation", fiatValuation)
                .toString();
    }

    public String getWithdrawing() {
        return withdrawing;
    }

    public void setWithdrawing(String withdrawing) {
        this.withdrawing = withdrawing;
    }

    public String getBtcValuation() {
        return btcValuation;
    }

    public void setBtcValuation(String btcValuation) {
        this.btcValuation = btcValuation;
    }

    public String getFiatValuation() {
        return fiatValuation;
    }

    public void setFiatValuation(String fiatValuation) {
        this.fiatValuation = fiatValuation;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }
}
