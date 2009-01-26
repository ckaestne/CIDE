package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compEnd extends GenASTNode {
  public compEnd(comp_op comp_op, expr expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<comp_op>("comp_op", comp_op),
      new PropertyOne<expr>("expr", expr)
    }, firstToken, lastToken);
  }
  public compEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compEnd(cloneProperties(),firstToken,lastToken);
  }
  public comp_op getComp_op() {
    return ((PropertyOne<comp_op>)getProperty("comp_op")).getValue();
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
}
