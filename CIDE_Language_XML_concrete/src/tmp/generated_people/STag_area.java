package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class STag_area extends GenASTNode {
  public STag_area(ArrayList<Attribute> attribute, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Attribute>("attribute", attribute)
    }, firstToken, lastToken);
  }
  public STag_area(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new STag_area(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Attribute> getAttribute() {
    return ((PropertyZeroOrMore<Attribute>)getProperty("attribute")).getValue();
  }
}
