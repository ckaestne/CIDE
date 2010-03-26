/** GPL >= 2.0
 * Based upon RSS Reader MIDlet
 * Copyright (C) 2004 Gösta Jonasson <gosta(at)brothas.net>
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This was first modified no earlier than May 27, 2008.
 *
 */
// Expand to define MIDP define
//#define DMIDP20
// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING

//#ifdef DMIDP20
package net.eiroca.j2me.RSSReader.presentation;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import cz.cacek.ebook.AbstractView;
import com.substanceofcode.utils.EncodingUtil;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif


public class RenderedWord {

  public static int fontSize = -1;
  public static int heightFont;
  public static Font[] font;
  public static int[] fontWidth;

  public int offset;
  public int row;
  public int style;
  public int color;
  public String word;
  private static boolean debug = false;

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("RenderedWord");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

  /** Createt array of fonts with style index 'i'. */
  static {
    RenderedWord.font = new Font[7];
    RenderedWord.fontWidth = new int[7];
    RenderedWord.updFontData(Font.SIZE_MEDIUM);
  }

  public static void updFontData(int fontSize) {
    if (RenderedWord.fontSize == fontSize) {
		return;
	}
    RenderedWord.fontSize = fontSize;
    for (int i = 0; i < 7; i++) {
	  Font f = Font.getFont(Font.FACE_PROPORTIONAL, i, fontSize);
      RenderedWord.font[i] = f;
      RenderedWord.fontWidth[i] = f.stringWidth(" ");
    }
    RenderedWord.heightFont = Font.getFont(Font.FACE_PROPORTIONAL,
			Font.STYLE_PLAIN, fontSize).getHeight() +
			AbstractView.getLineSpace();
  }

  /**
   * Create a RenderedWord which is used to display HTML minmally formatted
   * and font. A 'word' may be several words concatenated with blanks that
   * are to be displayed with the same atributes.
   *
   * Constructor
   * @param offset
   * @param row
   * @param style
   * @param color
   * @param word
   * @author Irv Bunton
   */
  public RenderedWord(final int offset, final int row, final int style, final int color, final String word) {
    this.offset = offset;
    this.style = style;
    this.word = word;
    this.color = color;
    this.row = row;
  }

  /**
   * Create list of words with color, style, x, y, height and word text
   *
   * @param text
   * @param width
   * @param height
   * @param colTxt
   * @param colTit
   * @return    Vector
   * @author Irv Bunton
   */
  public static final Vector createWordList(final String text, final int width,
		  final int height, final int colTxt, final int colTit) {
	  //#ifdef DLOGGING
	  Logger logger = Logger.getLogger( "RenderedWord");
      boolean finestLoggable = logger.isLoggable(Level.FINEST);
	  if (finestLoggable){logger.finest("text=" + text);}
	  //#endif
	  final Vector wordList = new Vector();
	  RenderedWord oldWord;
	  final String tmpText = text.replace('\n', ' ');
	  char[] ctmpText = tmpText.toCharArray();
	  int apos = tmpText.indexOf('&');
	  boolean ampPres;
	  if (apos >= 0) {
		  int spos = tmpText.indexOf(';');
		  ampPres = (apos < spos);
	  } else {
		  ampPres = false;
	  }
	  int color = colTxt;
	  int index = 0;
	  final int lung = tmpText.length();
	  int row = 0;
	  int offset = 0;
	  int style = 0;
	  boolean fine = false;
	  String tag = "";
	  oldWord = null;
	  while (!fine) {
		  int spaceIndex;
		  int tagIndex;
		  int endTagIndex;
		  String word = "";
		  if (ctmpText[index] == ' ') {
			  index++;
			  if (index == lung) {
				  fine = true;
			  }
			  word = " ";
		  }
		  else {
			  if (ctmpText[index] == '<') {
				  endTagIndex = tmpText.indexOf(">", index);
				  if (endTagIndex < 0) {
					  fine = true;
				  }
				  else {
					  tag = tmpText.substring(index + 1, endTagIndex).toLowerCase(
							  ).trim();
					  int pos = tag.indexOf(" ");
					  if (pos >= 0) {
						  tag = tag.substring(0, pos);
					  }
					  int tagLen = tag.length();
					  //#ifdef DTEST
					  if (debug) {
						  //#ifdef DLOGGING
						  if (finestLoggable){logger.finest("tag,tagLen=" + tag + "," + tagLen);}
						  //#endif
					  }
					  //#endif
					  if (tagLen >= 1) {
						  switch(tag.charAt(0)) {
							  case 'b':
								  if (tagLen == 1) {
									  style |= Font.STYLE_BOLD;
								  } else
									  if ((tag.equals("br"))
											  || (tag.equals("br/"))) {
										  row += RenderedWord.heightFont;
										  offset = 0;
									  }
								  break;
							  case 'c':
								  if ((tagLen == 4) &&
										  tag.equals("cite")) {
									  style |= Font.STYLE_ITALIC;
								  }
								  break;
							  case 'e':
								  if (tag.equals("em")) {
									  style |= Font.STYLE_ITALIC;
								  }
								  break;
							  case 'h':
								  if (tagLen >= 2) {
									  char schar = tag.charAt(1);
									  if ((schar == 'r') &&
											  ((tagLen == 2) ||
											   tag.equals("hr/"))) {
										  row += RenderedWord.heightFont;
										  offset = 0;
									  }
									  else
										  if ((tagLen == 2) && ('1' <= schar) &&
												  (schar <= '6')) {
											  style |= Font.STYLE_BOLD;
											  /* If not at the beginning of the
												 line, need to start a new
												 line so that the header is
												 alone. */
											  if (offset != 0) {
												  row += RenderedWord.heightFont;
												  offset = 0;
											  }
										  }
								  }
								  break;
							  case 'i':
								  if (tagLen == 1) {
									  style |= Font.STYLE_ITALIC;
								  }
								  else if ((tagLen == 3) && tag.equals("ins")) {
									  style |= Font.STYLE_UNDERLINED;
								  }
								  break;
							  case 'l':
								  if ((tagLen == 2) && tag.equals("li")) {
									  row += RenderedWord.heightFont;
									  offset = 0;
								  }
								  break;
							  case 'p':
								  if (tagLen == 1) {
									  row += RenderedWord.heightFont;
									  offset = 0;
								  }
								  break;
							  case 's':
								  if (tag.equals("strong")) {
									  style |= Font.STYLE_BOLD;
								  }
								  break;
							  case 't':
								  if (tag.equals("tit")) {
									  color = colTit;
									  style |= Font.STYLE_BOLD;
								  }
								  break;
							  case 'u':
								  if (tagLen == 1) {
									  style |= Font.STYLE_UNDERLINED;
								  }
								  else if ((tagLen == 2) &&
										  (tag.charAt(1) == 'l')) {
									  if (offset != 0) {
										  row += RenderedWord.heightFont;
										  offset = 0;
									  }
								  }
								  break;
							  case 'v':
								  if ((tagLen == 3) && tag.equals("var")) {
									  style |= Font.STYLE_ITALIC;
								  }
								  break;
							  case '/':
								  if (tagLen >= 2) {
									  switch(tag.charAt(1)) {
										  case 'b':
											  if (tagLen == 2) {
												  style &= ~Font.STYLE_BOLD;
											  }
											  break;
										  case 'c':
											  if ((tagLen == 5) &&
													  tag.equals("/cite")) {
												  style &= ~Font.STYLE_ITALIC;
											  }
											  break;
										  case 'e':
											  if (tag.equals("/em")) {
												  style &= ~Font.STYLE_ITALIC;
											  }
											  break;
										  case 'h':
											  if ((tagLen == 3) &&
													  ('1' <= tag.charAt(2)) &&
													  (tag.charAt(2) <= '6')) {
												  style &= ~Font.STYLE_BOLD;
												  row += RenderedWord.heightFont;
												  offset = 0;
											  }
											  break;
										  case 'i':
											  if (tagLen == 2) {
												  style &= ~Font.STYLE_ITALIC;
											  }
											  else if ((tagLen == 3) &&
													  tag.equals("ins")) {
												  style &= ~Font.STYLE_UNDERLINED;
											  }
											  break;
										  case 'p':
											  if (tagLen == 2) {
												  row += RenderedWord.heightFont;
												  offset = 0;
											  }
											  break;
										  case 's':
											  if (tag.equals("/strong")) {
												  style &= ~Font.STYLE_BOLD;
											  }
											  break;
										  case 't':
											  if (tag.equals("/tit")) {
												  color = colTxt;
												  style &= ~Font.STYLE_BOLD;
												  row += 14 * RenderedWord.heightFont / 10;
												  offset = 0;
											  }
											  break;
										  case 'u':
											  if (tagLen == 2) {
												  style &= ~Font.STYLE_UNDERLINED;
											  }
											  break;
										  case 'v':
											  if ((tagLen == 4) &&
													  tag.equals("/var")) {
												  style &= ~Font.STYLE_ITALIC;
											  }
											  break;
										  default:
											  break;
									  }
								  }
								  break;
							  default:
								  break;
						  }
					  }
					  if ((style < 0) || (style > 7)) {
						  style = 0;
					  }
					  index = endTagIndex + 1;
					  if ((index == lung) || (index == -1)) {
						  fine = true;
					  }
				  }
			  }
			  else {
				  spaceIndex = tmpText.indexOf(" ", index);
				  tagIndex = tmpText.indexOf("<", index);
				  if ((spaceIndex == -1) && (tagIndex == -1)) {
					  word = tmpText.substring(index);
					  fine = true;
				  }
				  else {
					  if ((spaceIndex == -1) && (tagIndex != -1)) {
						  word = tmpText.substring(index, tagIndex);
						  index = tagIndex;
					  }
					  else {
						  if ((spaceIndex != -1) && (tagIndex == -1)) {
							  word = tmpText.substring(index, spaceIndex);
							  index = spaceIndex;
						  }
						  else {
							  if (spaceIndex < tagIndex) {
								  word = tmpText.substring(index, spaceIndex);
								  index = spaceIndex;
							  }
							  else {
								  word = tmpText.substring(index, tagIndex);
								  index = tagIndex;
							  }
						  }
					  }
				  }
				  if ((word != null) && (word.length() != 0)) {
					  if (ampPres) {
						  word = EncodingUtil.replaceAlphaEntities(true, word);
					  }
					  final int l = RenderedWord.font[style].stringWidth(word);
					  int pos = offset;
					  if ((offset + l) < width) {
						  offset = offset + l + RenderedWord.fontWidth[style];
					  }
					  else {
						  if (offset != 0) {
							  row += RenderedWord.heightFont;
						  }
						  pos = 0;
						  offset = l + RenderedWord.fontWidth[style];
					  }
					  if (oldWord == null) {
						  oldWord = new RenderedWord(pos, row, style, color, word);
					  }
					  else {
						  if ((oldWord.row == row) && (oldWord.style == style) && (oldWord.color == color)) {
							  oldWord.word += " " + word;
						  }
						  else {
							  wordList.addElement(oldWord);
							  oldWord = new RenderedWord(pos, row, style, color, word);
						  }
					  }
				  }
			  }
		  }
	  }
	  if (oldWord != null) {
		  wordList.addElement(oldWord);
	  }
	  return wordList;
  }

}
//#endif
