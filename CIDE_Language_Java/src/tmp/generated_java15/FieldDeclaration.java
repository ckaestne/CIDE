package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FieldDeclaration extends GenASTNode {
  public FieldDeclaration(Type type, VariableDeclarator variableDeclarator, ArrayList<VariableDeclarator> variableDeclarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type),
      new PropertyOne<VariableDeclarator>("variableDeclarator", variableDeclarator),
      new PropertyZeroOrMore<VariableDeclarator>("variableDeclarator1", variableDeclarator1)
    }, firstToken, lastToken);
  }
  public FieldDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FieldDeclaration(cloneProperties(),firstToken,lastToken);
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
