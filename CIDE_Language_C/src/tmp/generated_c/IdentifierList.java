package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IdentifierList extends GenASTNode {
  public IdentifierList(ASTStringNode identifier, ArrayList<ASTStringNode> identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrMore<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public IdentifierList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IdentifierList(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ArrayList<ASTStringNode> getIdentifier1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
