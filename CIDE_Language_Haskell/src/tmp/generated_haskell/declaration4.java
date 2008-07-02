package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration4 extends declaration {
  public declaration4(patr patr, expr expr1, whereDecls whereDecls2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<patr>("patr", patr),
      new PropertyOne<expr>("expr1", expr1),
      new PropertyZeroOrOne<whereDecls>("whereDecls2", whereDecls2)
    }, firstToken, lastToken);
  }
  public declaration4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration4(cloneProperties(),firstToken,lastToken);
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
  public expr getExpr1() {
    return ((PropertyOne<expr>)getProperty("expr1")).getValue();
  }
  public whereDecls getWhereDecls2() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls2")).getValue();
  }
}
