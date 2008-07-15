package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeOp1 extends MultiplicativeOp {
  public MultiplicativeOp1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public MultiplicativeOp1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeOp1(cloneProperties(),firstToken,lastToken);
  }
}
