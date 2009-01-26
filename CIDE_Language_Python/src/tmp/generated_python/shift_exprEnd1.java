package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class shift_exprEnd1 extends shift_exprEnd {
  public shift_exprEnd1(arith_expr arith_expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<arith_expr>("arith_expr", arith_expr)
    }, firstToken, lastToken);
  }
  public shift_exprEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new shift_exprEnd1(cloneProperties(),firstToken,lastToken);
  }
  public arith_expr getArith_expr() {
    return ((PropertyOne<arith_expr>)getProperty("arith_expr")).getValue();
  }
}
