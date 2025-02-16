package Api;

import DTO.MainDTO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TbPublicWifiInfo {
	@SerializedName("row")
	private List<MainDTO> row;

	public List<MainDTO> getRow() {
		return row;
	}
}