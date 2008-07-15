package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeOperator1 extends MultiplicativeOperator {
  public MultiplicativeOperator1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public MultiplicativeOperator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeOperator1(cloneProperties(),firstToken,lastToken);
  }
}
