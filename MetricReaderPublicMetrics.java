package custom.aggregate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.util.Assert;

public class MetricReaderPublicMetrics implements PublicMetrics {

	private final MetricReader metricReader;

	public MetricReaderPublicMetrics(MetricReader metricReader) {
		Assert.notNull(metricReader, "MetricReader must not be null");
		this.metricReader = metricReader;
	}

	@Override
	public Collection<Metric<?>> metrics() {
		List<Metric<?>> result = new ArrayList<Metric<?>>();
		for (Metric<?> metric : this.metricReader.findAll()) {
			result.add(metric);
		}
		return result;
	}

}



interface PublicMetrics {

	/**
	 * Return an indication of current state through metrics.
	 * @return the public metrics
	 */
	Collection<Metric<?>> metrics();

}



interface MetricReader {

	/**
	 * Find an instance of the metric with the given name (usually the latest recorded
	 * value).
	 * @param metricName the name of the metric to find
	 * @return a metric value or null if there are none with that name
	 */
	Metric<?> findOne(String metricName);

	/**
	 * Find all the metrics known to this reader.
	 * @return all instances of metrics known to this reader
	 */
	Iterable<Metric<?>> findAll();

	/**
	 * The number of metrics known to this reader.
	 * @return the number of metrics
	 */
	long count();

}