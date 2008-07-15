package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameter extends GenASTNode {
  public FormalParameter(ASTTextNode text393, Type type, ASTTextNode text394, VariableDeclaratorId variableDeclaratorId, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text393", text393),
      new PropertyOne<Type>("type", type),
      new PropertyZeroOrOne<ASTTextNode>("text394", text394),
      new PropertyOne<VariableDeclaratorId>("variableDeclaratorId", variableDeclaratorId)
    }, firstToken, lastToken);
  }
  public FormalParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameter(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText393() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text393")).getValue();
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public ASTTextNode getText394() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text394")).getValue();
  }
  public VariableDeclaratorId getVariableDeclaratorId() {
    return ((PropertyOne<VariableDeclaratorId>)getProperty("variableDeclaratorId")).getValue();
  }
}
