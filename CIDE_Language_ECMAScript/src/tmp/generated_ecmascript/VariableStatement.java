package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableStatement extends GenASTNode {
  public VariableStatement(VariableDeclarationList variableDeclarationList, ASTTextNode text329, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableDeclarationList>("variableDeclarationList", variableDeclarationList),
      new PropertyZeroOrOne<ASTTextNode>("text329", text329)
    }, firstToken, lastToken);
  }
  public VariableStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableStatement(cloneProperties(),firstToken,lastToken);
  }
  public VariableDeclarationList getVariableDeclarationList() {
    return ((PropertyOne<VariableDeclarationList>)getProperty("variableDeclarationList")).getValue();
  }
  public ASTTextNode getText329() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text329")).getValue();
  }
}
