package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Name extends GenASTNode {
  public Name(ASTStringNode identifier_name, ArrayList<ASTStringNode> identifier_name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier_name", identifier_name),
      new PropertyZeroOrMore<ASTStringNode>("identifier_name1", identifier_name1)
    }, firstToken, lastToken);
  }
  public Name(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Name(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier_name() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier_name")).getValue();
  }
  public ArrayList<ASTStringNode> getIdentifier_name1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("identifier_name1")).getValue();
  }
}
