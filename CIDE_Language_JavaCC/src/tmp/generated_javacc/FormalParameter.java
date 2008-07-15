package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameter extends GenASTNode {
  public FormalParameter(ASTTextNode text501, Type type, ASTTextNode text502, VariableDeclaratorId variableDeclaratorId, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text501", text501),
      new PropertyOne<Type>("type", type),
      new PropertyZeroOrOne<ASTTextNode>("text502", text502),
      new PropertyOne<VariableDeclaratorId>("variableDeclaratorId", variableDeclaratorId)
    }, firstToken, lastToken);
  }
  public FormalParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameter(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText501() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text501")).getValue();
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public ASTTextNode getText502() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text502")).getValue();
  }
  public VariableDeclaratorId getVariableDeclaratorId() {
    return ((PropertyOne<VariableDeclaratorId>)getProperty("variableDeclaratorId")).getValue();
  }
}
