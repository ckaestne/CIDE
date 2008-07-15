package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclarator extends GenASTNode {
  public VariableDeclarator(VariableDeclaratorId variableDeclaratorId, VariableInitializer variableInitializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableDeclaratorId>("variableDeclaratorId", variableDeclaratorId),
      new PropertyZeroOrOne<VariableInitializer>("variableInitializer", variableInitializer)
    }, firstToken, lastToken);
  }
  public VariableDeclarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclarator(cloneProperties(),firstToken,lastToken);
  }
  public VariableDeclaratorId getVariableDeclaratorId() {
    return ((PropertyOne<VariableDeclaratorId>)getProperty("variableDeclaratorId")).getValue();
  }
  public VariableInitializer getVariableInitializer() {
    return ((PropertyZeroOrOne<VariableInitializer>)getProperty("variableInitializer")).getValue();
  }
}
