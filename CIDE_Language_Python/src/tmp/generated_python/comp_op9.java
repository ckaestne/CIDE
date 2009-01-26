package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op9 extends comp_op {
  public comp_op9(ASTStringNode in1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("in1", in1)
    }, firstToken, lastToken);
  }
  public comp_op9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op9(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIn1() {
    return ((PropertyOne<ASTStringNode>)getProperty("in1")).getValue();
  }
}
