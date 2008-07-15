package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionReturnType extends GenASTNode {
  public FunctionReturnType(ASTTextNode text26, ASTTextNode text27, ASTTextNode text28, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text26", text26),
      new PropertyZeroOrOne<ASTTextNode>("text27", text27),
      new PropertyZeroOrOne<ASTTextNode>("text28", text28),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public FunctionReturnType(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionReturnType(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText26() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text26")).getValue();
  }
  public ASTTextNode getText27() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text27")).getValue();
  }
  public ASTTextNode getText28() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text28")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
