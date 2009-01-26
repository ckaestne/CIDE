package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class suite1 extends suite {
  public suite1(simple_stmt simple_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<simple_stmt>("simple_stmt", simple_stmt)
    }, firstToken, lastToken);
  }
  public suite1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new suite1(cloneProperties(),firstToken,lastToken);
  }
  public simple_stmt getSimple_stmt() {
    return ((PropertyOne<simple_stmt>)getProperty("simple_stmt")).getValue();
  }
}
