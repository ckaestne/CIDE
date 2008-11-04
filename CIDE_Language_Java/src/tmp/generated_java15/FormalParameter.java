package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameter extends GenASTNode {
  public FormalParameter(ASTTextNode text394, Type type, ASTTextNode text395, VariableDeclaratorId variableDeclaratorId, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text394", text394),
      new PropertyOne<Type>("type", type),
      new PropertyZeroOrOne<ASTTextNode>("text395", text395),
      new PropertyOne<VariableDeclaratorId>("variableDeclaratorId", variableDeclaratorId)
    }, firstToken, lastToken);
  }
  public FormalParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameter(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText394() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text394")).getValue();
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public ASTTextNode getText395() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text395")).getValue();
  }
  public VariableDeclaratorId getVariableDeclaratorId() {
    return ((PropertyOne<VariableDeclaratorId>)getProperty("variableDeclaratorId")).getValue();
  }
}
