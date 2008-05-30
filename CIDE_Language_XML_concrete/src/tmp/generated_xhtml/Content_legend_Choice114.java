package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_legend_Choice114 extends Content_legend_Choice1 {
  public Content_legend_Choice114(Element_em element_em, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_em>("element_em", element_em)
    }, firstToken, lastToken);
  }
  public Content_legend_Choice114(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_legend_Choice114(cloneProperties(),firstToken,lastToken);
  }
  public Element_em getElement_em() {
    return ((PropertyOne<Element_em>)getProperty("element_em")).getValue();
  }
}
