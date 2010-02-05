package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LocalVariableDeclaration extends GenASTNode {
  public LocalVariableDeclaration(ASTTextNode text79, Type type, VariableDeclarator variableDeclarator, ArrayList<VariableDeclarator> variableDeclarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text79", text79),
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
  public ASTTextNode getText79() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text79")).getValue();
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
