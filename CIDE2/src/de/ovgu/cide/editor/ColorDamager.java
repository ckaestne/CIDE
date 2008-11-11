package de.ovgu.cide.editor;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.presentation.IPresentationDamager;

import de.ovgu.cide.features.source.ColoredSourceFile;

public class ColorDamager implements IPresentationDamager {

	public ColorDamager(ColoredSourceFile sourceFile) {
	}

	public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event,
			boolean documentPartitioningChanged) {
		return partition;
	}

	public void setDocument(IDocument document) {
	}

}
