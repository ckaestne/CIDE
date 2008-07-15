package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class NonTerminal extends GenASTNode {
  public NonTerminal(ASTStringNode identifier, ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public NonTerminal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new NonTerminal(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
