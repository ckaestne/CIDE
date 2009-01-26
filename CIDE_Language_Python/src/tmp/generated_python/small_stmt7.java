package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt7 extends small_stmt {
  public small_stmt7(global_stmt global_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<global_stmt>("global_stmt", global_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt7(cloneProperties(),firstToken,lastToken);
  }
  public global_stmt getGlobal_stmt() {
    return ((PropertyOne<global_stmt>)getProperty("global_stmt")).getValue();
  }
}
