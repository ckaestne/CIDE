package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comparison extends GenASTNode {
  public comparison(expr expr, ArrayList<compEnd> compEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr),
      new PropertyZeroOrMore<compEnd>("compEnd", compEnd)
    }, firstToken, lastToken);
  }
  public comparison(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comparison(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public ArrayList<compEnd> getCompEnd() {
    return ((PropertyZeroOrMore<compEnd>)getProperty("compEnd")).getValue();
  }
}
