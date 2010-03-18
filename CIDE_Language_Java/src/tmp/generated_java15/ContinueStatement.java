package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ContinueStatement extends GenASTNode {
  public ContinueStatement(ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public ContinueStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ContinueStatement(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
