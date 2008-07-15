package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclarationList extends GenASTNode {
  public VariableDeclarationList(VariableDeclaration variableDeclaration, ArrayList<VariableDeclaration> variableDeclaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableDeclaration>("variableDeclaration", variableDeclaration),
      new PropertyZeroOrMore<VariableDeclaration>("variableDeclaration1", variableDeclaration1)
    }, firstToken, lastToken);
  }
  public VariableDeclarationList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclarationList(cloneProperties(),firstToken,lastToken);
  }
  public VariableDeclaration getVariableDeclaration() {
    return ((PropertyOne<VariableDeclaration>)getProperty("variableDeclaration")).getValue();
  }
  public ArrayList<VariableDeclaration> getVariableDeclaration1() {
    return ((PropertyZeroOrMore<VariableDeclaration>)getProperty("variableDeclaration1")).getValue();
  }
}
