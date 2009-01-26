package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class try_stmt extends GenASTNode {
  public try_stmt(suite suite, tryEnd tryEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<suite>("suite", suite),
      new PropertyOne<tryEnd>("tryEnd", tryEnd)
    }, firstToken, lastToken);
  }
  public try_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new try_stmt(cloneProperties(),firstToken,lastToken);
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
  public tryEnd getTryEnd() {
    return ((PropertyOne<tryEnd>)getProperty("tryEnd")).getValue();
  }
}
