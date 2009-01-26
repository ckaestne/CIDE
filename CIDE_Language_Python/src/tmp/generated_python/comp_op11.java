package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op11 extends comp_op {
  public comp_op11(ASTStringNode is1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("is1", is1)
    }, firstToken, lastToken);
  }
  public comp_op11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op11(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIs1() {
    return ((PropertyOne<ASTStringNode>)getProperty("is1")).getValue();
  }
}
