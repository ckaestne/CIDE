package coloredide;



public interface IColorChangeListener {
	void astColorChanged(ASTColorChangedEvent event);
	void fileColorChanged(FileColorChangedEvent event);
	void colorListChanged(ColorListChangedEvent event);
}
