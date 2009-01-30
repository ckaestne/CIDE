package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprlist extends GenASTNode {
  public exprlist(expr expr, ArrayList<expr> expr1, ASTTextNode text587, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr),
      new PropertyZeroOrMore<expr>("expr1", expr1),
      new PropertyZeroOrOne<ASTTextNode>("text587", text587)
    }, firstToken, lastToken);
  }
  public exprlist(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprlist(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public ArrayList<expr> getExpr1() {
    return ((PropertyZeroOrMore<expr>)getProperty("expr1")).getValue();
  }
  public ASTTextNode getText587() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text587")).getValue();
  }
}
