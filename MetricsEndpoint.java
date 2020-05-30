package custom.aggregate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

/**
 * {@link Endpoint} to expose a collection of {@link PublicMetrics}.
 *
 * @author Dave Syer
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "endpoints.metrics")
public class MetricsEndpoint extends AbstractEndpoint<Map<String, Object>> {

	private final List<PublicMetrics> publicMetrics;

	/**
	 * Create a new {@link MetricsEndpoint} instance.
	 * @param publicMetrics the metrics to expose
	 */
	public MetricsEndpoint(PublicMetrics publicMetrics) {
		this(Collections.singleton(publicMetrics));
	}

	/**
	 * Create a new {@link MetricsEndpoint} instance.
	 * @param publicMetrics the metrics to expose. The collection will be sorted using the
	 * {@link AnnotationAwareOrderComparator}.
	 */
	public MetricsEndpoint(Collection<PublicMetrics> publicMetrics) {
		super("metrics");
		Assert.notNull(publicMetrics, "PublicMetrics must not be null");
		this.publicMetrics = new ArrayList<PublicMetrics>(publicMetrics);
		AnnotationAwareOrderComparator.sort(this.publicMetrics);
	}

	public void registerPublicMetrics(PublicMetrics metrics) {
		this.publicMetrics.add(metrics);
		AnnotationAwareOrderComparator.sort(this.publicMetrics);
	}

	public void unregisterPublicMetrics(PublicMetrics metrics) {
		this.publicMetrics.remove(metrics);
	}

	@Override
	public Map<String, Object> invoke() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		List<PublicMetrics> metrics = new ArrayList<PublicMetrics>(this.publicMetrics);
		for (PublicMetrics publicMetric : metrics) {
			try {
				for (Metric<?> metric : publicMetric.metrics()) {
					result.put(metric.getName(), metric.getValue());
				}
			}
			catch (Exception ex) {
				// Could not evaluate metrics
			}
		}
		return result;
	}

}