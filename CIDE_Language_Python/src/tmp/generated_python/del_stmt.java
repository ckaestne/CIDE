package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class del_stmt extends GenASTNode {
  public del_stmt(ASTStringNode del, exprlist exprlist, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("del", del),
      new PropertyOne<exprlist>("exprlist", exprlist)
    }, firstToken, lastToken);
  }
  public del_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new del_stmt(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDel() {
    return ((PropertyOne<ASTStringNode>)getProperty("del")).getValue();
  }
  public exprlist getExprlist() {
    return ((PropertyOne<exprlist>)getProperty("exprlist")).getValue();
  }
}
