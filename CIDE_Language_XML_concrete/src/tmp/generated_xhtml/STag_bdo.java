package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class STag_bdo extends GenASTNode {
  public STag_bdo(ArrayList<Attribute> attribute, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Attribute>("attribute", attribute)
    }, firstToken, lastToken);
  }
  public STag_bdo(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new STag_bdo(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Attribute> getAttribute() {
    return ((PropertyZeroOrMore<Attribute>)getProperty("attribute")).getValue();
  }
}
