package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration1 extends declaration {
  public declaration1(function function, expr expr, whereDecls whereDecls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<function>("function", function),
      new PropertyOne<expr>("expr", expr),
      new PropertyZeroOrOne<whereDecls>("whereDecls", whereDecls)
    }, firstToken, lastToken);
  }
  public declaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration1(cloneProperties(),firstToken,lastToken);
  }
  public function getFunction() {
    return ((PropertyOne<function>)getProperty("function")).getValue();
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public whereDecls getWhereDecls() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls")).getValue();
  }
}
