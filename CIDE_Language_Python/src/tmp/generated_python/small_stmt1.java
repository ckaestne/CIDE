package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt1 extends small_stmt {
  public small_stmt1(expr_stmt expr_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr_stmt>("expr_stmt", expr_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt1(cloneProperties(),firstToken,lastToken);
  }
  public expr_stmt getExpr_stmt() {
    return ((PropertyOne<expr_stmt>)getProperty("expr_stmt")).getValue();
  }
}
