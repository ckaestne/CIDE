package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameterList extends GenASTNode {
  public FormalParameterList(Identifier identifier, ArrayList<Identifier> identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier),
      new PropertyZeroOrMore<Identifier>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public FormalParameterList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameterList(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
  public ArrayList<Identifier> getIdentifier1() {
    return ((PropertyZeroOrMore<Identifier>)getProperty("identifier1")).getValue();
  }
}
