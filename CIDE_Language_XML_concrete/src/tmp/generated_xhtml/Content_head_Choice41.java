package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Choice41 extends Content_head_Choice4 {
  public Content_head_Choice41(Element_script element_script, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Element_script>("element_script", element_script)
    }, firstToken, lastToken);
  }
  public Content_head_Choice41(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Choice41(cloneProperties(),firstToken,lastToken);
  }
  public Element_script getElement_script() {
    return ((PropertyOne<Element_script>)getProperty("element_script")).getValue();
  }
}
