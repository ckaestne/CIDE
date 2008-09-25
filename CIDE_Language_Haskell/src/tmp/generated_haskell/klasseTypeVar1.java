package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gparser.Token;

public class klasseTypeVar1 extends klasseTypeVar {
  public klasseTypeVar1(ASTStringNode variable_id, ArrayList<type> type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("variable_id", variable_id),
      new PropertyZeroOrMore<type>("type", type)
    }, firstToken, lastToken);
  }
  public klasseTypeVar1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new klasseTypeVar1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVariable_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("variable_id")).getValue();
  }
  public ArrayList<type> getType() {
    return ((PropertyZeroOrMore<type>)getProperty("type")).getValue();
  }
}
