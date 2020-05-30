package custom.aggregate;

import java.util.Map;

public interface Endpoint<T> {
	public boolean isEnabled();
	public boolean isSensitive();
	String getId();
	Map<String, Object> invoke();
}
