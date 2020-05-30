package custom.aggregate;

public interface AggregateApplication {

	/**
	 * Retrieves the bindable proxy instance (e.g.
	 * {@link org.springframework.cloud.stream.messaging.Processor},
	 * {@link org.springframework.cloud.stream.messaging.Source},
	 * {@link org.springframework.cloud.stream.messaging.Sink} or custom interface) from
	 * the given namespace.
	 *
	 * @param bindableType the bindable type
	 * @param namespace the namespace
	 * @param <T>
	 * @return
	 */
	<T> T getBinding(Class<T> bindableType, String namespace);
}