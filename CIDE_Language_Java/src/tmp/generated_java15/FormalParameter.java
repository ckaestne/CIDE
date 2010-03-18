package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameter extends GenASTNode {
  public FormalParameter(ASTTextNode text22, Type type, ASTTextNode text23, VariableDeclaratorId variableDeclaratorId, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text22", text22),
      new PropertyOne<Type>("type", type),
      new PropertyZeroOrOne<ASTTextNode>("text23", text23),
      new PropertyOne<VariableDeclaratorId>("variableDeclaratorId", variableDeclaratorId)
    }, firstToken, lastToken);
  }
  public FormalParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameter(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText22() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text22")).getValue();
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public ASTTextNode getText23() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text23")).getValue();
  }
  public VariableDeclaratorId getVariableDeclaratorId() {
    return ((PropertyOne<VariableDeclaratorId>)getProperty("variableDeclaratorId")).getValue();
  }
}
