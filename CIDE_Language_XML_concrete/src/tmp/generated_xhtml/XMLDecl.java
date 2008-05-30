package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class XMLDecl extends GenASTNode {
  public XMLDecl(ArrayList<Attribute> attribute, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Attribute>("attribute", attribute)
    }, firstToken, lastToken);
  }
  public XMLDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new XMLDecl(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Attribute> getAttribute() {
    return ((PropertyOneOrMore<Attribute>)getProperty("attribute")).getValue();
  }
}
