package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_caption_Choice122 extends Content_caption_Choice1 {
  public Content_caption_Choice122(Element_cite element_cite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_cite>("element_cite", element_cite)
    }, firstToken, lastToken);
  }
  public Content_caption_Choice122(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_caption_Choice122(cloneProperties(),firstToken,lastToken);
  }
  public Element_cite getElement_cite() {
    return ((PropertyOne<Element_cite>)getProperty("element_cite")).getValue();
  }
}
