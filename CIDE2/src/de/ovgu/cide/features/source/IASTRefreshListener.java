package de.ovgu.cide.features.source;

import java.util.EventListener;

public interface IASTRefreshListener extends EventListener {

	public void astRefreshed(ColoredSourceFile sourceFile);
	
}
