package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class newlineOrStmt2 extends newlineOrStmt {
  public newlineOrStmt2(stmt stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<stmt>("stmt", stmt)
    }, firstToken, lastToken);
  }
  public newlineOrStmt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newlineOrStmt2(cloneProperties(),firstToken,lastToken);
  }
  public stmt getStmt() {
    return ((PropertyOne<stmt>)getProperty("stmt")).getValue();
  }
}
