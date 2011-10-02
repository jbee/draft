package de.jbee.jtrail.content;

import java.lang.ref.SoftReference;

public class ModuleLoader<M extends Module> {

	public SoftReference<M> moduleRef;

	public final Class<M> moduleClass;

	public ModuleLoader( Class<M> cls ) {
		this.moduleClass = cls;
	}

	public M load() {
		if ( moduleRef != null && moduleRef.get() != null )
			return moduleRef.get();
		try {
			moduleRef = new SoftReference<M>( moduleClass.newInstance() );
		} catch ( InstantiationException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( IllegalAccessException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
