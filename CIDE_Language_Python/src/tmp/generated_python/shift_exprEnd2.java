package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class shift_exprEnd2 extends shift_exprEnd {
  public shift_exprEnd2(arith_expr arith_expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<arith_expr>("arith_expr1", arith_expr1)
    }, firstToken, lastToken);
  }
  public shift_exprEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new shift_exprEnd2(cloneProperties(),firstToken,lastToken);
  }
  public arith_expr getArith_expr1() {
    return ((PropertyOne<arith_expr>)getProperty("arith_expr1")).getValue();
  }
}
