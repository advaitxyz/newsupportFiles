package custom.aggregate;

interface PropertyNamePatternsMatcher {

	PropertyNamePatternsMatcher ALL = new PropertyNamePatternsMatcher() {

		@Override
		public boolean matches(String propertyName) {
			return true;
		}

	};

	PropertyNamePatternsMatcher NONE = new PropertyNamePatternsMatcher() {

		@Override
		public boolean matches(String propertyName) {
			return false;
		}

	};

	/**
	 * Return {@code true} of the property name matches.
	 * @param propertyName the property name
	 * @return {@code true} if the property name matches
	 */
	boolean matches(String propertyName);

}