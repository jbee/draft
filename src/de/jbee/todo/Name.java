package de.jbee.todo;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public final class Name<T> {

	private static final Map<Class<?>, Map<String, Name<?>>> NAMES = new IdentityHashMap<Class<?>, Map<String, Name<?>>>();

	private final Class<T> namespace;
	private final String name;

	private Name( Class<T> namespace, String name ) {
		super();
		this.namespace = namespace;
		this.name = name;
	}

	public static <T> Name<T> of( String name, Class<T> namespace ) {
		Map<String, Name<?>> ns = NAMES.get( namespace );
		if ( ns == null ) {
			ns = new HashMap<String, Name<?>>();
			NAMES.put( namespace, ns );
		}
		Name<?> n = ns.get( name );
		if ( n == null ) {
			n = new Name<T>( namespace, name );
			ns.put( name, n );
		}
		@SuppressWarnings ( "unchecked" )
		final Name<T> res = (Name<T>) n;
		return res;
	}

	@Override
	public String toString() {
		return name + "[" + namespace.getSimpleName() + "]";
	}
}
