package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class stmt1 extends stmt {
  public stmt1(simple_stmt simple_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<simple_stmt>("simple_stmt", simple_stmt)
    }, firstToken, lastToken);
  }
  public stmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new stmt1(cloneProperties(),firstToken,lastToken);
  }
  public simple_stmt getSimple_stmt() {
    return ((PropertyOne<simple_stmt>)getProperty("simple_stmt")).getValue();
  }
}
