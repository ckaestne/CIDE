package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class STag_dl extends GenASTNode {
  public STag_dl(ArrayList<Attribute> attribute, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Attribute>("attribute", attribute)
    }, firstToken, lastToken);
  }
  public STag_dl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new STag_dl(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Attribute> getAttribute() {
    return ((PropertyZeroOrMore<Attribute>)getProperty("attribute")).getValue();
  }
}
