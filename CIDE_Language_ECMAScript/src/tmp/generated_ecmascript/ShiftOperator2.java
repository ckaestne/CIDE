package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftOperator2 extends ShiftOperator {
  public ShiftOperator2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public ShiftOperator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ShiftOperator2(cloneProperties(),firstToken,lastToken);
  }
}
