package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type_name extends GenASTNode {
  public type_name(identifier identifier, ArrayList<identifier> identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrMore<identifier>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public type_name(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type_name(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public ArrayList<identifier> getIdentifier1() {
    return ((PropertyZeroOrMore<identifier>)getProperty("identifier1")).getValue();
  }
}
