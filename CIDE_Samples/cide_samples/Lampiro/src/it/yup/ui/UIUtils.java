package it.yup.ui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class UIUtils {

	/**
	 * The lookup table used to memorize letters for search pattern
	 */
	public static char[][] itu_keys = { { ' ', '0' }, { '1' },
			{ 'a', 'b', 'c', 'x', '2' }, { 'd', 'e', 'f', 'x', 'x', '3' },
			{ 'g', 'h', 'i', 'x', '4' }, { 'j', 'k', 'l', '5' },
			{ 'm', 'n', 'o', 'x', '6' }, { 'p', 'q', 'r', 's', '7' },
			{ 't', 'u', 'v', 'x', '8' }, { 'w', 'x', 'y', 'z', '9' } };

	public static Image imageResize(Image image, int finalWidth, int finalHeight) {
		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();
		if (finalHeight == -1) finalHeight = finalWidth * sourceHeight
				/ sourceWidth;
		int out[] = new int[finalHeight * finalWidth];
		int rgb[] = new int[50 * 50];

		for (int srcXOrigin = 0; srcXOrigin < sourceWidth; srcXOrigin += 50) {
			for (int srcYOrigin = 0; srcYOrigin < sourceHeight; srcYOrigin += 50) {
				int srcBlockWidth = Math.min(50, sourceWidth - srcXOrigin);
				int srcBlockHeight = Math.min(50, sourceHeight - srcYOrigin);

				float destXOrigin = ((float) (srcXOrigin * finalWidth))
						/ sourceWidth;
				float destYOrigin = ((float) (srcYOrigin * finalHeight))
						/ sourceHeight;

				float destBlockWidth = ((float) (srcBlockWidth * finalWidth))
						/ sourceWidth;
				float destBlockHeight = ((float) (srcBlockHeight * finalHeight))
						/ sourceHeight;

				int destBlockWidthInt = (int) ((destBlockWidth + destXOrigin) - ((srcXOrigin * finalWidth) / sourceWidth));
				int destBlockHeightInt = (int) ((destBlockHeight + destYOrigin) - ((srcYOrigin * finalHeight) / sourceHeight));

				image.getRGB(rgb, 0, srcBlockWidth, srcXOrigin, srcYOrigin,
						srcBlockWidth, srcBlockHeight);
				//				int[] tempRGB = new int[sourceHeight * sourceWidth];
				//				image.getRGB(tempRGB, 0, sourceWidth, 0, 0, sourceWidth,
				//						sourceHeight);
				UIUtils.rescalaArray(out, rgb, srcBlockWidth, srcBlockHeight,
						destBlockWidthInt, destBlockHeightInt,
						(int) destXOrigin, (int) destYOrigin, finalWidth);
			}
		}

		return Image.createRGBImage(out, finalWidth, finalHeight, true);
	}

	public static void rescalaArray(int out[], int[] ini, int x, int y, int x2,
			int y2, int destXOrigin, int destYOrigin, int finalWidth) {
		for (int yy = 0; yy < y2; yy++) {
			int dy = yy * y / y2;
			for (int xx = 0; xx < x2; xx++) {
				int dx = xx * x / x2;
				out[(finalWidth * (yy + destYOrigin)) + xx + destXOrigin] = ini[(x * dy)
						+ dx];
			}
		}
	}

	public static int colorize(int inputColor, int percentage) {
		int[] rgb = new int[] { inputColor, inputColor, inputColor };
		int outputColor = 0;
		for (int i = 0; i < 3; i++) {
			rgb[i] &= (0xFF0000 >> (i * 8));
			int temp = rgb[i]  >> (16 -i * 8); 
			temp += ((0xFF * (percentage > 0 ? 1 : 0) - temp) * Math.abs(percentage) )/100;
			rgb[i] = temp << (16 -i * 8); 
			outputColor += rgb[i];
		}
		return outputColor;
	}
	
	/*
	 * An helper function that builds and initialize a UIMenu.
	 * 
	 * @param item
	 * 			the UIMenu title
	 * @param absoluteX
	 * 			the X position of the UIMenu
	 * @param absoluteY
	 * 			the Y position of the UIMenu
	 * @param width
	 * 			the width of the UIMenu
	 * @param firstItem
	 * 			the first item to add to the UIMenu  
	 */
	public static UIMenu easyMenu(String title, int absoluteX, int absoluteY,
			int width, UIItem firstItem) {
		UIMenu retMenu = new UIMenu(title);
		if (absoluteX > 0) retMenu.setAbsoluteX(absoluteX);
		if (absoluteY > 0) retMenu.setAbsoluteY(absoluteY);
		if (width > 0) retMenu.setWidth(width);
		if (firstItem != null) {
			retMenu.append(firstItem);
			retMenu.setSelectedItem(firstItem);
		}
		return retMenu;
	}
	
	public static UIMenu easyMenu(String title, int absoluteX, int absoluteY,
			int width, UIItem firstItem,String cancelString, String selectString) {
		UIMenu retMenu = UIUtils.easyMenu(title,absoluteX,absoluteY,width,firstItem);
		retMenu.cancelMenuString = cancelString;
		retMenu.selectMenuString = selectString;
		return retMenu;
	}


	/*
	 * An helper function that builds an horizontal layout of three items.
	 * The first and third item are dummy and the second is the passed item.
	 * Size is the size in pixel od the seconde layout element
	 * 
	 * @param item
	 * 			the item to insert in the middle of the layout
	 * @param size
	 * 			the size of the middle element of the layout  
	 */
	public static UIHLayout easyCenterLayout(UIItem item, int size) {
		UIHLayout buttonLayout = new UIHLayout(3);
		UISeparator dummySeparator = new UISeparator(0);
		if (item instanceof UILabel)
			((UILabel)item).setAnchorPoint(Graphics.HCENTER);
		buttonLayout.setGroup(false);
		buttonLayout.insert(dummySeparator, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);
		buttonLayout.insert(item, 1, size, UILayout.CONSTRAINT_PIXELS);
		buttonLayout.insert(dummySeparator, 2, 50, UILayout.CONSTRAINT_PERCENTUAL);
		return buttonLayout;
	}

	public static int medColor(int firstColor, int secondColor) {
		int[] rgb1 = new int[] { firstColor & 0xFF0000, firstColor& 0xFF00, firstColor & 0xFF};
		int[] rgb2 = new int[] { secondColor & 0xFF0000, secondColor& 0xFF00, secondColor & 0xFF};
		int outputColor = 0;
		for (int i = 0; i < 3; i++) {
			int temp = ( (rgb1[i]+rgb2[i])/2 ) & (0xFF0000 >> (i*8));
			outputColor+=temp;
		}
		return outputColor;
	}
}
