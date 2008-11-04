package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LocalVariableDeclaration extends GenASTNode {
  public LocalVariableDeclaration(ASTTextNode text559, Type type, VariableDeclarator variableDeclarator, ArrayList<VariableDeclarator> variableDeclarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text559", text559),
      new PropertyOne<Type>("type", type),
      new PropertyOne<VariableDeclarator>("variableDeclarator", variableDeclarator),
      new PropertyZeroOrMore<VariableDeclarator>("variableDeclarator1", variableDeclarator1)
    }, firstToken, lastToken);
  }
  public LocalVariableDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LocalVariableDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText559() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text559")).getValue();
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public VariableDeclarator getVariableDeclarator() {
    return ((PropertyOne<VariableDeclarator>)getProperty("variableDeclarator")).getValue();
  }
  public ArrayList<VariableDeclarator> getVariableDeclarator1() {
    return ((PropertyZeroOrMore<VariableDeclarator>)getProperty("variableDeclarator1")).getValue();
  }
}
