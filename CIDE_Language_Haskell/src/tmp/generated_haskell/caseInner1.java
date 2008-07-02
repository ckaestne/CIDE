package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class caseInner1 extends caseInner {
  public caseInner1(patr patr, expr expr, whereDecls whereDecls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<patr>("patr", patr),
      new PropertyOne<expr>("expr", expr),
      new PropertyZeroOrOne<whereDecls>("whereDecls", whereDecls)
    }, firstToken, lastToken);
  }
  public caseInner1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new caseInner1(cloneProperties(),firstToken,lastToken);
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public whereDecls getWhereDecls() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls")).getValue();
  }
}
