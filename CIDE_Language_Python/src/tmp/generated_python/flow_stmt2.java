package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class flow_stmt2 extends flow_stmt {
  public flow_stmt2(ASTStringNode continue_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("continue_kw", continue_kw)
    }, firstToken, lastToken);
  }
  public flow_stmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new flow_stmt2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getContinue_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("continue_kw")).getValue();
  }
}
