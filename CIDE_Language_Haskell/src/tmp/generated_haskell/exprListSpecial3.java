package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprListSpecial3 extends exprListSpecial {
  public exprListSpecial3(expr expr4, ArrayList<expr> expr5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr4", expr4),
      new PropertyOneOrMore<expr>("expr5", expr5)
    }, firstToken, lastToken);
  }
  public exprListSpecial3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new exprListSpecial3(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr4() {
    return ((PropertyOne<expr>)getProperty("expr4")).getValue();
  }
  public ArrayList<expr> getExpr5() {
    return ((PropertyOneOrMore<expr>)getProperty("expr5")).getValue();
  }
}
