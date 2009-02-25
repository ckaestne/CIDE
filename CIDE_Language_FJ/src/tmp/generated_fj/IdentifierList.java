package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IdentifierList extends GenASTNode {
  public IdentifierList(ArrayList<ASTStringNode> identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public IdentifierList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IdentifierList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTStringNode> getIdentifier() {
    return ((PropertyList<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
