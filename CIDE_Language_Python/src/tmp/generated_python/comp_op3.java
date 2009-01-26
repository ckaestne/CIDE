package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op3 extends comp_op {
  public comp_op3(ASTStringNode eqequal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("eqequal", eqequal)
    }, firstToken, lastToken);
  }
  public comp_op3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEqequal() {
    return ((PropertyOne<ASTStringNode>)getProperty("eqequal")).getValue();
  }
}
