package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class flow_stmt1 extends flow_stmt {
  public flow_stmt1(ASTStringNode break_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("break_kw", break_kw)
    }, firstToken, lastToken);
  }
  public flow_stmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new flow_stmt1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getBreak_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("break_kw")).getValue();
  }
}
