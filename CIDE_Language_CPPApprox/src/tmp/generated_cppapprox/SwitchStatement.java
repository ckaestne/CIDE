package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwitchStatement extends GenASTNode {
  public SwitchStatement(ASTStringNode findendcb, ArrayList<SwCase> swCase, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findendcb", findendcb),
      new PropertyZeroOrMore<SwCase>("swCase", swCase)
    }, firstToken, lastToken);
  }
  public SwitchStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwitchStatement(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindendcb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendcb")).getValue();
  }
  public ArrayList<SwCase> getSwCase() {
    return ((PropertyZeroOrMore<SwCase>)getProperty("swCase")).getValue();
  }
}
