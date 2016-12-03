package source;

public class ClientInformation {
	private String resolution;
	private String fps;

	public ClientInformation(String resolution, String fps) {
		this.resolution = resolution;
		this.fps = fps;
	}
	
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getFps() {
		return fps;
	}
	public void setFps(String fps) {
		this.fps = fps;
	}
}
