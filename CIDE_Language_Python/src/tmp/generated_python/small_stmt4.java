package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class small_stmt4 extends small_stmt {
  public small_stmt4(pass_stmt pass_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<pass_stmt>("pass_stmt", pass_stmt)
    }, firstToken, lastToken);
  }
  public small_stmt4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new small_stmt4(cloneProperties(),firstToken,lastToken);
  }
  public pass_stmt getPass_stmt() {
    return ((PropertyOne<pass_stmt>)getProperty("pass_stmt")).getValue();
  }
}
