package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclaratorId extends GenASTNode {
  public VariableDeclaratorId(ASTStringNode identifier, ArrayList<ASTTextNode> text390, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrMore<ASTTextNode>("text390", text390)
    }, firstToken, lastToken);
  }
  public VariableDeclaratorId(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclaratorId(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ArrayList<ASTTextNode> getText390() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text390")).getValue();
  }
}
