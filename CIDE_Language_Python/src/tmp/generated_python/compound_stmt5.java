package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compound_stmt5 extends compound_stmt {
  public compound_stmt5(funcdef funcdef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<funcdef>("funcdef", funcdef)
    }, firstToken, lastToken);
  }
  public compound_stmt5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compound_stmt5(cloneProperties(),firstToken,lastToken);
  }
  public funcdef getFuncdef() {
    return ((PropertyOne<funcdef>)getProperty("funcdef")).getValue();
  }
}
