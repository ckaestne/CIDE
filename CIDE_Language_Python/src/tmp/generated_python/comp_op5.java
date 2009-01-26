package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op5 extends comp_op {
  public comp_op5(ASTStringNode eqless, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("eqless", eqless)
    }, firstToken, lastToken);
  }
  public comp_op5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEqless() {
    return ((PropertyOne<ASTStringNode>)getProperty("eqless")).getValue();
  }
}
