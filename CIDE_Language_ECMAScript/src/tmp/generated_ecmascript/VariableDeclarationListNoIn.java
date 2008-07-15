package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclarationListNoIn extends GenASTNode {
  public VariableDeclarationListNoIn(VariableDeclarationNoIn variableDeclarationNoIn, ArrayList<VariableDeclarationNoIn> variableDeclarationNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableDeclarationNoIn>("variableDeclarationNoIn", variableDeclarationNoIn),
      new PropertyZeroOrMore<VariableDeclarationNoIn>("variableDeclarationNoIn1", variableDeclarationNoIn1)
    }, firstToken, lastToken);
  }
  public VariableDeclarationListNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclarationListNoIn(cloneProperties(),firstToken,lastToken);
  }
  public VariableDeclarationNoIn getVariableDeclarationNoIn() {
    return ((PropertyOne<VariableDeclarationNoIn>)getProperty("variableDeclarationNoIn")).getValue();
  }
  public ArrayList<VariableDeclarationNoIn> getVariableDeclarationNoIn1() {
    return ((PropertyZeroOrMore<VariableDeclarationNoIn>)getProperty("variableDeclarationNoIn1")).getValue();
  }
}
