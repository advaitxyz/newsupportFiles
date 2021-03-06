package custom.aggregate;

import java.util.Collection;

import org.springframework.util.PatternMatchUtils;

/**
 * {@link PropertyNamePatternsMatcher} that delegates to
 * {@link PatternMatchUtils#simpleMatch(String[], String)}.
 *
 * @author Phillip Webb
 */
class PatternPropertyNamePatternsMatcher implements PropertyNamePatternsMatcher {

	private final String[] patterns;

	PatternPropertyNamePatternsMatcher(Collection<String> patterns) {
		this.patterns = (patterns != null) ? patterns.toArray(new String[patterns.size()]) : new String[] {};
	}

	@Override
	public boolean matches(String propertyName) {
		return PatternMatchUtils.simpleMatch(this.patterns, propertyName);
	}

}