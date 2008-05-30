package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_ins_Choice124 extends Content_ins_Choice1 {
  public Content_ins_Choice124(Element_map element_map, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_map>("element_map", element_map)
    }, firstToken, lastToken);
  }
  public Content_ins_Choice124(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_ins_Choice124(cloneProperties(),firstToken,lastToken);
  }
  public Element_map getElement_map() {
    return ((PropertyOne<Element_map>)getProperty("element_map")).getValue();
  }
}
