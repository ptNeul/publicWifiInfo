package Api;

import com.google.gson.annotations.SerializedName;

public class WifiInfoResponse {
	@SerializedName("TbPublicWifiInfo")
	private TbPublicWifiInfo tbPublicWifiInfo;

	public TbPublicWifiInfo getTbPublicWifiInfo() {
		return tbPublicWifiInfo;
	}
}