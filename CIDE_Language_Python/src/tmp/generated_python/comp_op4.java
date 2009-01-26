package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op4 extends comp_op {
  public comp_op4(ASTStringNode eqgreater, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("eqgreater", eqgreater)
    }, firstToken, lastToken);
  }
  public comp_op4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEqgreater() {
    return ((PropertyOne<ASTStringNode>)getProperty("eqgreater")).getValue();
  }
}
