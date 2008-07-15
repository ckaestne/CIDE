package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Do extends CodeUnit_InBlock {
  public Do(DoStatement doStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DoStatement>("doStatement", doStatement)
    }, firstToken, lastToken);
  }
  public Do(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Do(cloneProperties(),firstToken,lastToken);
  }
  public DoStatement getDoStatement() {
    return ((PropertyOne<DoStatement>)getProperty("doStatement")).getValue();
  }
}
